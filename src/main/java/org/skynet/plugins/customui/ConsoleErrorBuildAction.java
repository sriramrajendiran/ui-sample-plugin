package org.skynet.plugins.customui;

import hudson.model.AbstractBuild;
import hudson.model.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriramr on 5/19/17.
 */
public class ConsoleErrorBuildAction implements Action {

    private boolean showErrors,showWarnings;
    private AbstractBuild<?,?> build;

    public boolean isShowErrors() {
        return showErrors;
    }

    public boolean isShowWarnings() {
        return showWarnings;
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }


    public ConsoleErrorBuildAction(final boolean showErrors,final boolean showWarnings, final AbstractBuild<?,?> build) {
        this.showErrors = showErrors;
        this.showWarnings = showWarnings;
        this.build = build;
    }


    public ArrayList<String> getErrorlogs(String logtype)
    {   ArrayList<String> errorlist = new ArrayList<String>();
        try {
            List<String> loglist = build.getLog(1000000);
            for (int i=0;i<loglist.size()-1;i++) {
                if (loglist.get(i).toLowerCase().startsWith(logtype)) {
                    errorlist.add(loglist.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorlist;
    }

    @Override
    public String getIconFileName() {
        return "/plugin/testExample/img/build-goals.png";
    }

    @Override
    public String getDisplayName() {
        return "Console Error Page";
    }

    @Override
    public String getUrlName() {
        return "consoleErrors";
    }
}

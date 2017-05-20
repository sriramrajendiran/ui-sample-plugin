package org.skynet.plugins.customui;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Descriptor;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;

/**
 * Created by sriramr on 5/19/17.
 */
public class ConsoleErrorPublisher extends Recorder {

    private boolean showErrors,showWarnings;

    public boolean isShowErrors() {
        return showErrors;
    }

    public boolean isShowWarnings() {
        return showWarnings;
    }

    @DataBoundConstructor
    public ConsoleErrorPublisher(boolean showErrors, boolean showWarnings) {
        this.showErrors = showErrors;
        this.showWarnings = showWarnings;
    }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {

        ConsoleErrorBuildAction buildAction = new ConsoleErrorBuildAction(isShowErrors(),isShowWarnings(),build);
        build.addAction(buildAction);

        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }


    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Add Errors to #Build Page";
        }
    }

}

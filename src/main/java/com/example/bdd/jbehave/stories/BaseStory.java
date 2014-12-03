package com.example.bdd.jbehave.stories;

import com.example.bdd.jbehave.reporters.ConsoleStoryReporter;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseStory extends JUnitStories {
    BaseStory() {
        configuredEmbedder().embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(false)
                .useStoryTimeoutInSecs(9000000L);
        useConfiguration(configuration());
    }

    private URL getStoryUrl() {
        URL storyURL = null;
        try {
            // This requires you to start Maven from the project directory
            storyURL = new URL("file://" + System.getProperty("user.dir")
                    + "/src/main/resources/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return storyURL;
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(
                        new LoadFromRelativeFile(getStoryUrl()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withReporters(new ConsoleStoryReporter())
                                .withFormats(
                                        Format.CONSOLE,
                                        Format.HTML,
                                        Format.XML,
                                        Format.STATS))
                .usePendingStepStrategy(new FailingUponPendingStep());
    }

}
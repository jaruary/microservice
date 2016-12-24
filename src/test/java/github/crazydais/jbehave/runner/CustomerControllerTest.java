package github.crazydais.jbehave.runner;

import github.crazydais.jbehave.steps.CustomerControllerTestSteps;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

public class CustomerControllerTest extends BaseJbehaveTest {

    @Override
    protected List<String> storyPaths () {

        return new StoryFinder().findPaths(
            CodeLocations.codeLocationFromPath("src/test/resources/"),
            "story/customer_controller_test.story", "");
    }

    @Override
    public InjectableStepsFactory stepsFactory () {

        return new InstanceStepsFactory(configuration(),
            new CustomerControllerTestSteps());
    }
}

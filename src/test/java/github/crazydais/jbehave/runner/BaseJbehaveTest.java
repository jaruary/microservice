package github.crazydais.jbehave.runner;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.junit.runner.RunWith;

@RunWith(JUnitReportingRunner.class)
public abstract class BaseJbehaveTest extends JUnitStories {

    @Override
    public Configuration configuration () {

        return new MostUsefulConfiguration();
    }
}

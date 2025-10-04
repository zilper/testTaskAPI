package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.simple.SimpleLogger;

public class Hooks {

    public static Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
        log.trace("Trace start here");
    }

    @After
    public void afterScenario() {
    }

}

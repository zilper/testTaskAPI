package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static context.CacheKey.*;

public class Hooks {

    public static Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp() {
//        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        log.info("Logs start here");
    }

    @After
    public void tearDown() {
        clearCache(RESPONSE);
        log.info("Cache has been cleared");
    }

}

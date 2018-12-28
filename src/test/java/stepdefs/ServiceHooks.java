package stepdefs;

import Utils.Utilities;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.AfterStep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by thilinaga on 7/3/2017.
 */
public class ServiceHooks {
    static final Logger logger = LogManager.getLogger(ServiceHooks.class.getName());

   public static Scenario scenario;
    @Before
    public void initializeTest(Scenario scenario){
        // Code to setup initial configurations
        this.scenario=scenario;
        logger.info("Service hooks Before "+ scenario.getName());
        Utilities.InitializeProps();

    }

    @BeforeStep
    public void beforeStep() {


    }

    @AfterStep
    public void afterStep() {
        logger.info("Service hooks After Step "+ scenario.getName());
        String html= "<Table><TR height=1500px><TD>Request</TD><TD>test1</TD></TR><TR><TD>Reaponse</TD><TD>test2</TD></TR></TABLE>";
        ServiceHooks.scenario.embed(html.getBytes(),"text/html");
        if (scenario.isFailed()) {

        }
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        logger.info("Service hooks After "+ scenario.isFailed());
        if (scenario.isFailed()) {
            try {
                // Code to capture and embed images in test reports (if scenario fails)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
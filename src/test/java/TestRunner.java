/**
 * Created by thilinaga on 7/3/2017.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.apache.logging.log4j.core.appender.RollingFileAppender;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        tags = {"@GitLogin"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
       )
public class TestRunner extends AbstractTestNGCucumberTests {

   // Scenario scenario;
    /*@Before
    public void initializeTest(Scenario scenario){
        // Code to setup initial configurations
        this.scenario=scenario;



    }
*/

       /*
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        logger.info("Welcome Before Class");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    }
    @BeforeTest
    public void setUpTest(Scenario scenarios) throws Exception {
        logger.info("Welcome Before Test name" + scenarios.getName() );


    }


    @BeforeSuite
    public void setUpSuite() throws Exception {
        logger.info("Welcome Before Suite");


    }
    @BeforeMethod
    public void setUpMethod() throws Exception {
        logger.info("Welcome Before Method");


    }

    @Before
    public void setUp(Scenario scenario) throws Exception {
        logger.info("Welcome Before  name" );
        logger.info(scenario.getName());


    }


    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        //logger.info("Welcome");
        logger.info("Welcome Test");
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {

        logger.info("Welcome After Class");
        testNGCucumberRunner.finish();
    }*/
}
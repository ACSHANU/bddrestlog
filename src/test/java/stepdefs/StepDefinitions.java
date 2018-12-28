package stepdefs;

import Utils.Utilities;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Utils.RestEvidence;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.commons.codec.binary.Base64;

import static httpConnection.HttpClientConnections.*;

/**
 * Created by thilinaga on 7/3/2017.
 */
public class StepDefinitions {

    static final Logger logger = LogManager.getLogger(StepDefinitions.class.getName());

    public static void main(String[] args) {

        byte[] encodedBytes = Base64.encodeBase64("".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));

    }

    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String arg1, String arg2) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        logger.info("config file : " + System.getenv("log4j.configurationFile"));
        logger.info("config file : " + System.getenv("log4j.configurationFile"));
        logger.info("config file : " + System.getProperty("log4j.configurationFile"));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String responseBody;
        String resHeaders = "";
        final String url = "http://dummy.restapiexample.com/api/v1/employees";
        try {
            HttpGet httpget = new HttpGet(url);


            System.out.println("Executing request " + httpget.getRequestLine());


            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        HttpResponse response) throws ClientProtocolException, IOException {
                    StringBuilder str = new StringBuilder();
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        for (Header header : response.getAllHeaders()) {
                            logger.info(header.getName() + ":" + header.getValue());
                            str.append(header.getName() + ":" + header.getValue() + ",");
                        }

                        RestEvidence oEvi = new RestEvidence();

                        oEvi.setUrl(url);
                        oEvi.setStatusCode(Integer.toString(status));
                        oEvi.setResHeaders(str.toString());
                        oEvi.setRequest("");
                        Utilities.writeHTML(oEvi);
                        return entity != null ? EntityUtils.toString(entity) : null;

                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpclient.execute(httpget, responseHandler);


            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }


        logger.info("INFO : i_am_on_the_page_on_URL");
        logger.warn("WARN : i_am_on_the_page_on_URL");
        logger.error("ERROR : i_am_on_the_page_on_URL");
        logger.trace("TRACE : i_am_on_the_page_on_URL");
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();

    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();

    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();

    }

    @Then("^I should see the \"([^\"]*)\" button$")
    public void i_should_see_the_button(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();

    }

    @Given("^I access \"([^\"]*)\"$")
    public void iAccess(final String path) throws Throwable
    {

            String responseBody;
        Map<String,String>  queryString= new HashMap<String,String>();
        queryString.put("query","san");
            final URI uri = buildURI(path,queryString);
            final HttpGet httpget = new HttpGet(uri);
            logger.info(getHTTPResponse(httpget));




}

    @Then("^I should see the list of emals$")
    public void iShouldSeeTheListOfEmals() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
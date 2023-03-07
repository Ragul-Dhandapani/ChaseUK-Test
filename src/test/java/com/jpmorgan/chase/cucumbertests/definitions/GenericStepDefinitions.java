package com.jpmorgan.chase.cucumbertests.definitions;

import com.jpmorgan.chase.executor.RequestExecutor;
import com.jpmorgan.chase.constants.ApplicationConstants;
import com.jpmorgan.chase.cucumbertests.constants.TestApplicationConstants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.jpmorgan.chase.cucumbertests.constants.TestApplicationConstants.baseUri;

public class GenericStepDefinitions extends RequestExecutor {


    @Given("the base URI is {string}")
    public void theBaseURIIs(String baseUri) {
        TestApplicationConstants.baseUri = baseUri;
    }

    @When("user send a GET request to {string}")
    public void userSendAGETRequestTo(String postIdUri) {
        response=executeGET(baseUri + postIdUri);
    }

    @Then("the response status code should be {int} and reason  {string}")
    public void theResponseStatusCodeShouldBeStatus_codeAndReasonStatus_reason(int statusCode , String statusReason) {
        response.assertThat().statusCode(statusCode);
        response.assertThat().statusLine(statusReason);
    }

    @And("the response {string} schema {string} should match with specification")
    public void theResponseSchemaShouldMatchWithSpecification(String schemaFormat , String schemaFileName) {

        Assert.assertTrue(schemaValidator(response , schemaFormat ,
                        ApplicationConstants.SCHEMA_FILE_PATH + schemaFileName + "." + schemaFormat.toLowerCase()) ,
                "Schema validation failed !");
    }

}

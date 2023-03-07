package com.jpmorgan.chase.cucumbertests.definitions;

import com.jpmorgan.chase.executor.RequestExecutor;
import io.cucumber.java.en.When;

import static com.jpmorgan.chase.cucumbertests.constants.TestApplicationConstants.baseUri;

public class UserDetailsStepDefinitions extends RequestExecutor {

    @When("user retrieve the information for {string} user with their ID {int}")
    public void userRetrieveTheInformationForUserWithTheirIDUser_id(String endpoint , int userId) {
        response = executeGET(baseUri + endpoint + userId);
    }

    @When("user retrieve the information for {string} user with their invalid ID {string}")
    public void userRetrieveTheInformationForUserWithTheirInvalidID(String endpoint , String userId) {
        response=executeGET(baseUri+endpoint+userId);
    }
}

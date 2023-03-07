package com.jpmorgan.chase.cucumbertests.definitions;

import com.jpmorgan.chase.executor.RequestExecutor;
import com.jpmorgan.chase.cucumbertests.entity.Comments;
import com.jpmorgan.chase.validator.ResponseValidator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.jpmorgan.chase.cucumbertests.constants.TestApplicationConstants.baseUri;

public class CommentsStepDefinitions extends RequestExecutor {

    @When("user create a new comment for {string} with invalid request body {string}")
    public void userCreateANewCommentForWithInvalidRequestBody(String endpoint , String requestBody) {
        response=executePOST(baseUri+endpoint , requestBody);
    }

    @When("user create a new comment {string} with {string}, {string}, and {string} for post ID {int}")
    public void userCreateANewCommentWithAndForPostID(String endpoint , String name , String emailId , String body , int postId) {
        Comments comments = Comments.builder().postId(postId).name(name).email(emailId).body(body).build();
        response=executePOST(baseUri+endpoint , comments.toStringJsonWithoutId());
    }

    @When("user wish to DELETE comments by {string}")
    public void userWishToDELETECommentsBy(String endpoint) {
        response =executeDELETE(baseUri + endpoint);
    }

    @And("response for the respective postId {int} should be returned along with {int}")
    public void responseForTheRespectivePostIdPostIdShouldBeReturnedAlongWithExpected_count(int postId ,
                                                                                            int expectedResponseCount) {
        //iterate over all the response object and check if post id is matches with input
        boolean result = validateResponseAndReturnList(response , "$..postId").stream()
                .allMatch(list -> list == postId);

        Assert.assertTrue(result , "Unable to find the comments for the post_id -" + postId);

        Assert.assertEquals(expectedResponseCount , validateResponseAndReturnList(response , "$..postId").size() ,
                postId + "- Post id comments count does not match with expected count ");
    }

    @And("each comment should have a valid email address")
    public void eachCommentShouldHaveAValidEmailAddress() {
        //iterate over all the response object and check if email id is in valid format

        boolean result = validateResponseAndReturnListInStr(response , "$..email").stream()
                .allMatch(ResponseValidator::verifyEmailId);
        Assert.assertTrue(result , "Email Id is not valid format or invalid domain");
    }
}

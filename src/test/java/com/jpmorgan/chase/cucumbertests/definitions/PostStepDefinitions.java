package com.jpmorgan.chase.cucumbertests.definitions;

import com.jpmorgan.chase.executor.RequestExecutor;
import com.jpmorgan.chase.cucumbertests.entity.MakePost;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.jpmorgan.chase.cucumbertests.constants.TestApplicationConstants.baseUri;

public class PostStepDefinitions extends RequestExecutor {

    @When("the user create a {string} with userId {int}, title {string} and body {string}")
    public void theUserCreateAWithUserIdTitleAndBody(String endpoint ,
                                                     int userId , String title , String body) {
        MakePost makePost = MakePost.builder().userId(userId).title(title).body(body).build();
        response=executePOST(baseUri + endpoint , makePost.toStringJsonWithoutId());
    }

    @When("user updates to existing {string} with userId {int}, title {string} and body {string}")
    public void userUpdatesToExistingWithUserIdUser_idTitleAndBody(String endpoint , int userId , String title ,
                                                                   String body) {
        MakePost makePost = MakePost.builder().id(Integer.parseInt(endpoint.split("/")[2]))
                .userId(userId)
                .title(title)
                .body(body)
                .build();
        response=executePUT(baseUri + endpoint , makePost.toStringJsonWithId());
    }

    @When("user wish to DELETE posts {string}")
    public void userWishToDELETEPosts(String endpoint) {
        response=executeDELETE(baseUri + endpoint);
    }


    @Then("more then one user's posts should be in response data")
    public void moreThenOneUserSPostsShouldBeInResponseData() {

        Assert.assertTrue(validateResponseInSet(response , "$..userId").size() >= 1 ,
                "posts aren't from more then one userId");
    }

    @Then("all the posts made by the user with userId {int} should be returned")
    public void allThePostsMadeByTheUserWithUserIdShouldBeReturned(int userIdValue) {
        Assert.assertEquals(String.valueOf(userIdValue) , validateResponseInString(response , "$.userId"));
    }


}

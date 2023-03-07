package com.jpmorgan.chase.cucumbertests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static com.jpmorgan.chase.constants.ApplicationConstants.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = CUCUMBER_OPTIONS_FEATURES + "MakePosts.feature",
        plugin = {CUCUMBER_PLUGIN_PRETTY , CUCUMBER_PLUGIN_ALLURE , CUCUMBER_PLUGIN_JSON , CUCUMBER_PLUGIN_RERUN},
        glue = {CUCUMBER_OPTIONS_GLUE})
public class MakePostsRunner {

   /*
     extends AbstractTestNGCucumberTests{ //for parallel test run in testng
   @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }*/
}

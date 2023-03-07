package com.jpmorgan.chase.constants;

/**
 * This Class is used to set all the Static Constant Variable which can be used across the project
 *
 * @author Ragul Dhandapani
 */
public class ApplicationConstants {

    private ApplicationConstants(){}

    //cucumber options and plugin constants
    public static final String CUCUMBER_OPTIONS_FEATURES = "src/test/resources/features/";
    public static final String CUCUMBER_OPTIONS_GLUE = "com.jpmorgan.chase.cucumbertests";
    public static final String CUCUMBER_PLUGIN_PRETTY = "pretty";
    public static final String CUCUMBER_PLUGIN_JSON = "json:build/reports/cucumber-reports/cucumber-html-reports/cucumber.json";
    public static final String CUCUMBER_PLUGIN_RERUN = "rerun:build/reports/cucumber-reports/failed_scenarios.txt";
    public static final String CUCUMBER_PLUGIN_ALLURE ="io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm";

    public static final String SCHEMA_FILE_PATH = System.getProperty("user.dir")+ "/src/test/resources/schema/";

}

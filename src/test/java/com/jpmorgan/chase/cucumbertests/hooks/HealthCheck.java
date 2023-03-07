package com.jpmorgan.chase.cucumbertests.hooks;

import com.jpmorgan.chase.exceptions.HealthCheckFailedException;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class HealthCheck {

    private static boolean beforeAllExecuted = false;
    public static final String baseUrl = "https://jsonplaceholder.typicode.com";

    @SneakyThrows
    @BeforeAll
    public static void before_or_after_all(){

        if (!beforeAllExecuted) {
            try {
                beforeAllExecuted = true;

                Arrays.asList("/posts","/comments","/users").forEach(HealthCheck::checkGetApis);
                Arrays.asList("/posts/1","/comments/1").forEach(HealthCheck::checkDeleteApis);

                // Perform health check for POST APIs
                RestAssured.given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201);

                // Perform health check for PUT API
                RestAssured.given()
                        .baseUri(baseUrl)
                        .contentType(ContentType.JSON)
                        .body("{\"id\": 1, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
                        .when()
                        .put("/posts/1")
                        .then()
                        .statusCode(200);


                // Health check for /users API using POST method with relevant request body
                String requestBody = "{\n"
                        + "  \"name\": \"John Doe\",\n"
                        + "  \"username\": \"johndoe\",\n"
                        + "  \"email\": \"johndoe@example.com\",\n"
                        + "  \"address\": {\n"
                        + "    \"street\": \"123 Main St\",\n"
                        + "    \"suite\": \"Apt. 4B\",\n"
                        + "    \"city\": \"Anytown\",\n"
                        + "    \"zipcode\": \"12345-6789\",\n"
                        + "    \"geo\": {\n"
                        + "      \"lat\": \"-37.3159\",\n"
                        + "      \"lng\": \"81.1496\"\n"
                        + "    }\n"
                        + "  },\n"
                        + "  \"phone\": \"(123) 456-7890\",\n"
                        + "  \"website\": \"johndoe.com\",\n"
                        + "  \"company\": {\n"
                        + "    \"name\": \"Acme Inc\",\n"
                        + "    \"catchPhrase\": \"Catchy phrase\",\n"
                        + "    \"bs\": \"BS\"\n"
                        + "  }\n"
                        + "}";

                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .baseUri(baseUrl)
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201);

             log.info("Health Check passed");

            } catch (Exception e) {
                // Throw a custom exception to handle the health check failure
                throw new HealthCheckFailedException("API health check failed: " + e.getMessage());
            }
        }
    }

    private static void checkGetApis(String api) {

        RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get(api)
                .then()
                .statusCode(200);

    }


    private static void checkDeleteApis(String api) {

        RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .delete(api)
                .then()
                .statusCode(200);

    }
}

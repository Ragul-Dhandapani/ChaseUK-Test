package com.jpmorgan.chase.executor;

import com.jpmorgan.chase.validator.ResponseValidator;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestExecutor extends ResponseValidator {

    public static ValidatableResponse response;

    public static ValidatableResponse executeGET(String endPoint) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .get(endPoint)
                .then()
                .log()
                .all();
        

    }

    public static ValidatableResponse executePOST(String endPoint , String requestBody) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON).log().all()
                .when()
                .body(requestBody)
                .post(endPoint)
                .then()
                .log()
                .all();
    }

    public static ValidatableResponse executePUT(String endPoint , String requestBody) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .log()
                .all()
                .put(endPoint)
                .then()
                .log()
                .all();
        

    }

    public static ValidatableResponse executeDELETE(String endPoint) {

       return given()
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .delete(endPoint)
                .then()
                .log()
                .all();
        
    }

    public static ValidatableResponse executeGET(String endPoint , Map<String, String> endPointHeader) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .get(endPoint)
                .then()
                .log()
                .all();

        
    }

    public static ValidatableResponse executeGET(String endPoint , Map<String, String> endPointHeader ,
                                          ContentType contentType) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .get(endPoint)
                .then()
                .log()
                .all();
        

    }

    public static ValidatableResponse executeGETWithQueryParam(String endPoint ,
                                                 Map<String, String> queryParam) {

        return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .get(endPoint)
                .then()
                .log()
                .all();



    }

    // Added overload method of GET to add query parameter
    public static ValidatableResponse executeGET(String endPoint ,
                                          Map<String, String> endPointHeader ,
                                          Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .get(endPoint)
                .then()
                .log()
                .all();

        

    }

    // POST without body
    public static ValidatableResponse executePOST(String endPoint , Map<String, String> endPointHeader) {

       return given()
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .log()
                .all().when().post(endPoint).then().log().all();

        

    }

    public static ValidatableResponse executePOST(String endPoint , Map<String, String> endPointHeader , String requestBody) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .body(requestBody)
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    public static ValidatableResponse executePOST(String endPoint ,
                                           Map<String, String> endPointHeader ,
                                           String fileParam ,
                                           File file) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .headers(endPointHeader)
                .multiPart(fileParam , file)
                .log()
                .all()
                .when()
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    public static ValidatableResponse executePOST(String endPoint ,
                                           Map<String, String> endPointHeader ,
                                           String requestBody ,
                                           ContentType contentType) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .body(requestBody)
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    // Added overload method of POST to add query parameter
    public static ValidatableResponse executePOST(String endPoint ,
                                           Map<String, String> endPointHeader ,
                                           String requestBody ,
                                           Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .body(requestBody)
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    // Added overload method of POST to add query parameter (Multipart file)
    public static ValidatableResponse executePOST(String endPoint ,
                                           Map<String, String> endPointHeader ,
                                           String fileParam ,
                                           File file ,
                                           Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .headers(endPointHeader)
                .multiPart(fileParam , file)
                .log()
                .all()
                .when()
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    // Added overload method of POST to add query parameter with different
    // content type
    public static ValidatableResponse executePOST(String endPoint ,
                                           Map<String, String> endPointHeader ,
                                           String requestBody ,
                                           ContentType contentType ,
                                           Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .body(requestBody)
                .post(endPoint)
                .then()
                .log()
                .all();

        

    }

    public ValidatableResponse executePUT(String endPoint , Map<String, String> endPointHeader , String requestBody) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .put(endPoint)
                .then()
                .log()
                .all();

        
    }

    public ValidatableResponse executePUT(String endPoint ,
                                          Map<String, String> endPointHeader ,
                                          String requestBody ,
                                          ContentType contentType) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .put(endPoint)
                .then()
                .log()
                .all();

        

    }

    // Added overload method of PUT to add query parameter
    public ValidatableResponse executePUT(String endPoint ,
                                          Map<String, String> endPointHeader ,
                                          String requestBody ,
                                          Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .filter(new AllureRestAssured())
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .put(endPoint)
                .then()
                .log()
                .all();

        

    }

    // Added overload method of PUT to add query parameter with content type
    public ValidatableResponse executePUT(String endPoint ,
                                          Map<String, String> endPointHeader ,
                                          String requestBody ,
                                          ContentType contentType ,
                                          Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .filter(new AllureRestAssured())
                .relaxedHTTPSValidation()
                .contentType(contentType)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .put(endPoint)
                .then()
                .log()
                .all();

        

    }

    public static ValidatableResponse executeDELETE(String endPoint , Map<String, String> endPointHeader , String requestBody) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .delete(endPoint)
                .then()
                .log()
                .all();

        

    }

    public static ValidatableResponse executeDELETE(String endPoint ,
                                             Map<String, String> endPointHeader ,
                                             String requestBody ,
                                             ContentType contentType) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .delete(endPoint)
                .then()
                .log()
                .all();

        

    }

    public ValidatableResponse executeDELETEWithContentTypeAny(String endPoint , Map<String, String> endPointHeader) {

       return given().relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.ANY)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .delete(endPoint)
                .then()
                .log()
                .all();

        

    }

    public ValidatableResponse executeDELETE(String endPoint ,
                                             Map<String, String> endPointHeader ,
                                             String requestBody ,
                                             Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .filter(new AllureRestAssured())
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .delete(endPoint)
                .then()
                .log()
                .all();

        

    }

    public static ValidatableResponse executeDELETE(String endPoint ,
                                             Map<String, String> endPointHeader ,
                                             String requestBody ,
                                             ContentType contentType ,
                                             Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(contentType)
                .headers(endPointHeader)
                .when()
                .body(requestBody)
                .log()
                .all()
                .delete(endPoint)
                .then()
                .log()
                .all();

        
    }

    public static ValidatableResponse executeDELETE(String endPoint ,
                                             Map<String, String> endPointHeader ,
                                             Map<String, String> queryParam) {

       return given().queryParams(queryParam)
                .relaxedHTTPSValidation()
                .filter(new AllureRestAssured())
                .contentType(ContentType.ANY)
                .headers(endPointHeader)
                .log()
                .all()
                .when()
                .delete(endPoint)
                .then()
                .log()
                .all();

        
    }
}

package com.jpmorgan.chase.validator;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Slf4j
public class ResponseValidator {

    public static boolean verifyEmailId(String emailId) {

        EmailValidator emailValidator = EmailValidator.getInstance();
        boolean isEmailValid = emailValidator.isValid(emailId);
        String validationResult = isEmailValid ? "Valid" : "Invalid";
        log.info("The Email Address {} of user is {}",emailId, validationResult);
        return isEmailValid;
    }

    public String validateResponseInString(ValidatableResponse response , String regExPath) {
        return JsonPath.read(response.extract().body().asString() , regExPath).toString();
    }

    public List<Integer> validateResponseAndReturnList(ValidatableResponse response , String regPath) {
        return JsonPath.read(response.extract().body().asString() , regPath);
    }

    public List<String> validateResponseAndReturnListInStr(ValidatableResponse response , String regPath) {
        return JsonPath.read(response.extract().body().asString() , regPath);
    }

    public Set<String> validateResponseInSet(ValidatableResponse response , String regPath) {
        return new LinkedHashSet<>(JsonPath.read(response.extract().body().asString() , regPath));
    }

    public boolean schemaValidator(ValidatableResponse response , String format , String schemaFilePath) {
        if ("json".equalsIgnoreCase(format)) {
            response.assertThat().contentType(ContentType.JSON)
                    .and()
                    .body(matchesJsonSchema(new File(schemaFilePath)));
            return true;
        }
        return false;

    }

}
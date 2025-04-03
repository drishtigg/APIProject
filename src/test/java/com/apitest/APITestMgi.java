package com.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class APITestMgi {

    private static final String BASE_URL = "https://lb-core.testbed.maverick.verdis.internal:8362";
    private static final String AUTHORIZATION_HEADER = "Basic bGFsaXQ6bGFraG90aWE=";

    @BeforeClass
    public void setup() {
        // Disable SSL certificate validation globally
        RestAssured.useRelaxedHTTPSValidation();
    }

    private void sendPostRequest(String requestFilePath) throws Exception {
        // Read request body and send POST request
        String requestBody = new String(Files.readAllBytes(Paths.get(requestFilePath)));
        
        // Send the POST request and assert status
        given()
            .baseUri(BASE_URL)
            .header("Authorization", AUTHORIZATION_HEADER)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(200)  // Assert status code is 200 (OK)
            .log().body();  // Log the response body
    }

    @Test
    public void testErpWrite() throws Exception {
        // Call the method with the request file path
        sendPostRequest("src/test/resources/ERPWriteRequest/erpWrite.json");
    }
}

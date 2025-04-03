package com.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class APITestEDA {

    private static final String BASE_URL = "https://lb-core.testbed.verdis.internal:9475";
    private static final String AUTHORIZATION_HEADER = "Basic bGFsaXQ6bGFraG90aWE=";

    @BeforeClass
    public void setup() {
        // Disable SSL certificate validation globally
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Reusable method to send POST request and log the response
    private void sendPostRequest(String requestFilePath, String schemaFilePath) throws Exception {
        String requestBody = new String(Files.readAllBytes(Paths.get(requestFilePath)));

        given()
            .baseUri(BASE_URL) // Set base URL
            .header("Authorization", AUTHORIZATION_HEADER) // Set authorization header
            .contentType(ContentType.JSON) // Set content type as JSON
            .body(requestBody) // Set the request body read from the file
        .when()
            .post() // Send POST request
        .then()
            .statusCode(200) // Assert that the status code is 200 (OK)
            .log().body() // Log the full response body
            .assertThat()
            .body(matchesJsonSchema(Paths.get(schemaFilePath).toFile())); // Validate against JSON schema
    }

    @Test
    public void testEDAreq() throws Exception {
        // Call the reusable method with the request and schema files
        sendPostRequest(
            "src/test/resources/EDArequest/testEDAreq.json",
            "src/test/resources/schemas/testEDASchema.json"
        );
    }
}

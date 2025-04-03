package com.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class APITest {

    private static final String BASE_URL = "https://lb-core.testbed.verdis.internal:8343";
    private static final String AUTHORIZATION_HEADER = "Basic bGFsaXQ6bGFraG90aWE=";

    // Base URI for the internal API
    @BeforeClass
    public void setup() {
        // Disable SSL certificate validation globally
        RestAssured.useRelaxedHTTPSValidation();
    }

    // Reusable method to send POST request and log the response
    private void sendPostRequest(String requestFilePath, String testName) throws Exception {
        // Log the test name before sending the request
        System.out.println("Executing Test: " + testName);
        
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
            //.body("status", equalTo(true)) // Asserting that "status" is true
            .log().body() // Log the full response body
            .log().headers() // Log the response headers (if you want headers as well)
            .log().ifValidationFails(); // Log the response only if validation fails
           // .body("_trid", notNullValue()) ;// Assert that '_trid' is present

        // Log the successful completion of the test
        System.out.println("Completed Test: " + testName);
        
        System.out.println("");
        
        
    }

    @Test
    public void testInternalAPI() throws Exception {
        // Call the reusable method with the request file for testInternalAPI
        sendPostRequest("src/test/resources/getIPData/testRequest.json", "testInternalAPI");
    }

    @Test
    public void testviqautomation() throws Exception {
        // Call the reusable method with the request file for testviqautomation
        sendPostRequest("src/test/resources/getIPData/viqautomation.json", "testviqautomation");
    }

    @Test
    public void testgetSystemFilters() throws Exception {
        // Call the reusable method with the request file for testgetSystemFilters
        sendPostRequest("src/test/resources/getIPData/getSystemFilters.json", "testgetSystemFilters");
    }

    @Test
    public void testnewTag() throws Exception {
        // Call the reusable method with the request file for testnewTag
        sendPostRequest("src/test/resources/getIPData/newTag.json", "testnewTag");
    }

    @Test
    public void testgetTags() throws Exception {
        // Call the reusable method with the request file for testgetTags
        sendPostRequest("src/test/resources/getIPData/getTags.json", "testgetTags");
    }

    @Test
    public void testupdateTag() throws Exception {
        // Call the reusable method with the request file for testupdateTag
        sendPostRequest("src/test/resources/getIPData/updateTag.json", "testupdateTag");
    }

    @Test
    public void testupdatecxo() throws Exception {
        // Call the reusable method with the request file for testupdatecxo
        sendPostRequest("src/test/resources/getIPData/updatecxo.json", "testupdatecxo");
    }

    @Test
    public void testregisterDashboard() throws Exception {
        // Call the reusable method with the request file for testregisterDashboard
        sendPostRequest("src/test/resources/getIPData/registerDashboard.json", "testregisterDashboard");
    }

    @Test
    public void testgetDashboard() throws Exception {
        // Call the reusable method with the request file for testgetDashboard
        sendPostRequest("src/test/resources/getIPData/getDashboard.json", "testgetDashboard");
    }

    @Test
    public void testdeleteItemFromDashboard() throws Exception {
        // Call the reusable method with the request file for testdeleteItemFromDashboard
        sendPostRequest("src/test/resources/getIPData/deleteItemFromDashboard.json", "testdeleteItemFromDashboard");
    }

    @Test
    public void testupdateItemInDashboard() throws Exception {
        // Call the reusable method with the request file for testupdateItemInDashboard
        sendPostRequest("src/test/resources/getIPData/updateItemInDashboard.json", "testupdateItemInDashboard");
    }

    @Test
    public void testfileUpload() throws Exception {
        // Call the reusable method with the request file for testfileUpload
        sendPostRequest("src/test/resources/getIPData/fileUpload.json", "testfileUpload");
    }

    @Test
    public void testfetchTCQI() throws Exception {
        // Call the reusable method with the request file for testfetchTCQI
        sendPostRequest("src/test/resources/getIPData/fetchTCQI.json", "testfetchTCQI");
    }

    @Test
    public void testgetmanualinterfacefiles() throws Exception {
        // Call the reusable method with the request file for testgetmanualinterfacefiles
        sendPostRequest("src/test/resources/getIPData/getmanualinterfacefiles.json", "testgetmanualinterfacefiles");
    }
}

/*
 * @Test public void testfetchTCQI() throws Exception { sendPostRequest(
 * "src/test/resources/getIPData/fetchTCQI.json", "testfetchTCQI", response -> {
 * // Specific assertions for fetchTCQI response.then() .statusCode(200)
 * .body("status", equalTo(true)) .body("data.infotype", equalTo("primary"))
 * .body("data.username", equalTo("yash.wb@verdis.ai"))
 * .body("field", instanceOf(String.class))
 *  .body("_trid",
 * equalTo("TRGW676A947FB86151305257F9FF")) .log().body(); }); }
 */
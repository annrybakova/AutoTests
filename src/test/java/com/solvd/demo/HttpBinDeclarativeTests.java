package com.solvd.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Declarative-style Rest-Assured tests without Carina.
 * Each endpoint is tested separately but structure mimics declarative interfaces.
 */
public class HttpBinDeclarativeTests {

    private static final String BASE_URL = "https://httpbin.org";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(testName = "Declarative /headers")
    public void testHeaders() {
        Response response = RestAssured
                .given()
                .get("/headers");

        Assert.assertEquals(response.statusCode(), 200, "Expected status 200");
        Assert.assertTrue(response.asString().contains("headers"), "Response should contain 'headers'");
    }

    @Test(testName = "Declarative /delay/1")
    public void testDelay() {
        Response response = RestAssured
                .given()
                .get("/delay/1");

        Assert.assertEquals(response.statusCode(), 200, "Expected status 200");
    }

    @Test(testName = "Declarative /uuid")
    public void testUUID() {
        Response response = RestAssured
                .given()
                .get("/uuid");

        Assert.assertEquals(response.statusCode(), 200, "Expected status 200");
        String uuid = response.jsonPath().getString("uuid");
        Assert.assertTrue(uuid.matches("^[0-9a-fA-F-]{36}$"), "UUID format is invalid");
    }

    @Test(testName = "Declarative /ip")
    public void testIP() {
        Response response = RestAssured
                .given()
                .get("/ip");

        Assert.assertEquals(response.statusCode(), 200, "Expected status 200");
        Assert.assertNotNull(response.jsonPath().getString("origin"), "IP should not be null");
    }

    @Test(testName = "Declarative /user-agent")
    public void testUserAgent() {
        Response response = RestAssured
                .given()
                .get("/user-agent");

        Assert.assertEquals(response.statusCode(), 200, "Expected status 200");
        Assert.assertTrue(response.asString().contains("user-agent"), "Response should contain 'user-agent'");
    }
}

package com.solvd.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HttpBinImperativeTests {

    private static final String BASE_URL = "https://httpbin.org";

    @Test(testName = "GET /get")
    public void testGetRequest() {
        Response response = RestAssured
                .given()
                .get(BASE_URL + "/get");

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }

    @Test(testName = "GET /ip")
    public void testGetIP() {
        Response response = RestAssured
                .given()
                .get(BASE_URL + "/ip");

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getString("origin") != null, "IP should not be null");
    }

    @Test(testName = "GET /uuid")
    public void testGetUUID() {
        Response response = RestAssured
                .given()
                .get(BASE_URL + "/uuid");

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getString("uuid").matches("^[0-9a-fA-F-]{36}$"), "UUID format invalid");
    }

    @Test(testName = "GET /user-agent")
    public void testGetUserAgent() {
        Response response = RestAssured
                .given()
                .get(BASE_URL + "/user-agent");

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getString("user-agent").contains("Java"), "User-Agent should contain 'Java'");
    }

    @Test(testName = "GET /status/404")
    public void testStatus404() {
        Response response = RestAssured
                .given()
                .get(BASE_URL + "/status/404");

        Assert.assertEquals(response.statusCode(), 404, "Status code should be 404");
    }
}

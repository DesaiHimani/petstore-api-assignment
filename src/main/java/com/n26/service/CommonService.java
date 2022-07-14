package com.n26.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class CommonService {
    private static final String BASE_URI = "https://petstore3.swagger.io/api/v3/";

    private final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given().auth().oauth2("special-key");
        setCommonParams();
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("api_key", "special-key");
        requestSpecification.headers(headers);
    }

    protected Response getRequest(String uri) {
        return requestSpecification.expect().when().get(prepareUri.apply(uri));
    }

    protected Response postRequest(String uri, Object body) {
        return requestSpecification.body(body).expect().when().post(prepareUri.apply(uri));
    }

    protected Response putRequest(String uri, Object body) {
        return requestSpecification.body(body).expect().when().put(prepareUri.apply(uri));
    }

    protected void deleteRequest(String uri) {
        requestSpecification.expect().when().delete(prepareUri.apply(uri));
    }
}
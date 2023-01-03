package org.example.rest.api.request.impl;

import io.restassured.response.Response;
import org.example.rest.api.request.Post;
import org.example.rest.api.request.Request;

import static io.restassured.RestAssured.given;

public class AuthorizationRequest extends Request implements Post {

    public Response post(String dto, String url){
        return given().
                header("Content-type", "application/json").
                body(dto).
                when().
                post(url).
                then().
                statusCode(200).extract().response();
    }
}

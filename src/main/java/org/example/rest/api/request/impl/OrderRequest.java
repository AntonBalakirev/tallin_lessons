package org.example.rest.api.request.impl;

import io.restassured.response.Response;
import org.example.rest.api.request.Delete;
import org.example.rest.api.request.Get;
import org.example.rest.api.request.Post;
import org.example.rest.api.request.Request;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderRequest extends Request implements Post, Get, Delete {

    public OrderRequest(Map<String, String> headers){
        this.headers = headers;
    }

    public Response post(String dto, String url){
        return given().
                headers(headers).
                body(dto).
                when().
                post(url).
                then().
                statusCode(200).extract().response();
    }

    public Response get(String url){
        return given().
                headers(headers).
                when().
                get(url).
                then().
                statusCode(200).extract().response();
    }

    public Response delete(String url){
        return given().
                headers(headers).
                when().
                delete(url).
                then().
                statusCode(200).extract().response();
    }
}

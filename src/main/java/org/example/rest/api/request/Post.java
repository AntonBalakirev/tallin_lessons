package org.example.rest.api.request;

import io.restassured.response.Response;

public interface Post {
    abstract Response post(String dto, String url);
}

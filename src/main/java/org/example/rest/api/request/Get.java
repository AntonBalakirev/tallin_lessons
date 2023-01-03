package org.example.rest.api.request;

import io.restassured.response.Response;

public interface Get {
    abstract Response get(String url);
}

package org.example.rest.api.request;

import io.restassured.response.Response;

public interface Delete {
    abstract Response delete(String url);
}

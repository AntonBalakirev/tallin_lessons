package org.example.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class RestTest {

    @Test
    public void testOrderGetTest() {
        given()
                .accept("*/*")
        .when()
                .get("http://51.250.6.164:8080/test-orders/{id}", 1)
        .then()
                .statusCode(200)
                .body("status", equalTo("OPEN"),
                        "id", equalTo(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void parameterizedRestTest(int id) {
        when()
                .get("http://51.250.6.164:8080/test-orders/{id}", id)
                .then()
                .statusCode(200)
                .body("status", equalTo("OPEN"),
                        "id", equalTo(id));
    }

    @Test
    public void testOrderPostTest() {
        given()
                .accept("*/*")
                .contentType("application/json")
                .body("""
                        {"status": "OPEN",
                          "courierId": 0,
                          "customerName": "string",
                          "customerPhone": "string",
                          "comment": "string",
                          "id": 0}""")
        .when()
                .post("http://51.250.6.164:8080/test-orders/")
        .then()
                .statusCode(200)
                .body("status", equalTo("OPEN"));
    }
}

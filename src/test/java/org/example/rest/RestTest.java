package org.example.rest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.rest.dto.Order;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void serializedOrderTest() {
//        Order requestOrder = new Order();
//
//        requestOrder.setStatus("OPEN");
//        requestOrder.setCustomerName("Pavel");
//        requestOrder.setCustomerPhone("1234567890");
//        requestOrder.setCourierId(123L);
//        requestOrder.setComment("comment");

        Order requestOrderConstructed =
                new Order("OPEN",
                        123L,
                        "Pavel",
                        "1234567890",
                        "comment");

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrderConstructed);

        given().contentType("application/json")
                .body(stringRequestOrder)
        .when()
                .post("http://51.250.6.164:8080/test-orders/")
        .then()
                .statusCode(200)
                .body("status", equalTo(requestOrderConstructed.getStatus()));
    }

    @Test
    public void deserializedOrderTest() {
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Pavel");
        requestOrder.setCustomerPhone("1234567890");
        requestOrder.setCourierId(123L);
        requestOrder.setComment("comment");

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrder);

        Response response =
                given()
                        .contentType("application/json")
                        .body(stringRequestOrder)
                .when()
                        .post("http://51.250.6.164:8080/test-orders/")
                .then()
                        .extract().response();

        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode, "Ошибка");

        String responseBody = response.body().asString();
        Order responseOrder = gson.fromJson(responseBody, Order.class);

        Assertions.assertEquals("OPEN", responseOrder.getStatus(), "Полученный статус сообщения отличается от ожидаемого");
    }

}

package org.example.rest;

import io.restassured.response.Response;
import org.example.rest.api.deprecated.LoginFunctions;
import org.example.rest.api.deprecated.TestOrderFunctions;
import org.example.rest.dto.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeliveryTest {

    static Map<String,String> headers = new HashMap<>();

    @BeforeAll
    public static void setup(){
        //авторизация
        headers.put("Content-type", "application/json");

        LoginFunctions loginFunctions = new LoginFunctions();
        String token = loginFunctions.getToken();

        headers.put("Authorization", "Bearer " + token);
    }

    @Test
    public void orderLifecycleTest(){
        //создать заказ
        Order requestOrder = new Order(
            "OPEN",
                123L,
                "Pavel",
                "1234567890",
                "comment"
        );

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();
        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);
        String orderId = String.valueOf(responseOrder.getId());

        //получить заказ по id
        Order orderById = testOrderFunctions.getOrderById(headers, orderId);
        Assertions.assertAll(
                () -> Assertions.assertEquals(requestOrder.getStatus(), orderById.getStatus()),
                () -> Assertions.assertEquals(requestOrder.getCustomerName(), orderById.getCustomerName()),
                () -> Assertions.assertEquals(requestOrder.getCustomerPhone(), orderById.getCustomerPhone())
        );

        //удалить заказ по id
        Assertions.assertTrue(testOrderFunctions.deleteOrderById(headers, orderId));
    }

    @Test
    public void getOrdersTest(){
        LoginFunctions loginFunctions = new LoginFunctions();
        Response responseOrders =
                given().
                        headers(headers).
                        when().
                        get(loginFunctions.getBaseUrl() + "/orders").
                        then().
                        statusCode(200).extract().response();
        Assertions.assertFalse(responseOrders.body().asString().isBlank());
        System.out.println(responseOrders.body().asString());
    }

    @Test
    public void postNewOrder(){
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Pavel");
        requestOrder.setCustomerPhone("1234567890");
        requestOrder.setCourierId(123L);
        requestOrder.setComment("comment");

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();
        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder);
        Assertions.assertEquals(
                requestOrder.getStatus(),
                responseOrder.getStatus(),
                "Полученный статус сообщения отличается от ожидаемого");
        System.out.println(responseOrder.toString());
    }
}

package org.example.rest;

import org.example.rest.api.controller.LoginController;
import org.example.rest.api.controller.OrderController;
import org.example.rest.dto.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UpdatedDeliveryTest {

    static LoginController loginController;
    static OrderController orderController;

    @BeforeAll
    public static void setup(){
        loginController = new LoginController();
        String studentToken = loginController.getToken();
        orderController = new OrderController(studentToken);
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

        Order responseOrder = orderController.postNewOrder(requestOrder);
        String orderId = String.valueOf(responseOrder.getId());

        //получить заказ по id
        Order orderById = orderController.getOrderById(orderId);
        Assertions.assertAll(
                () -> Assertions.assertEquals(requestOrder.getStatus(), orderById.getStatus()),
                () -> Assertions.assertEquals(requestOrder.getCustomerName(), orderById.getCustomerName()),
                () -> Assertions.assertEquals(requestOrder.getCustomerPhone(), orderById.getCustomerPhone())
        );

        //удалить заказ по id
        Assertions.assertTrue(orderController.deleteOrderById(orderId));
    }
}

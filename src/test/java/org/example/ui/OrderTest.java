package org.example.ui;

import org.junit.jupiter.api.Test;

public class OrderTest extends BaseTest{

    @Test
    public void createOrderTest() {
        loginPage
                .login("useraqa10", "hellouser123")
                .createOrder("Pavel", "1234568", "comment")
                .checkOrderCreatedText("Заказ создан!");
    }
}

package org.example.ui;

import org.example.ui.pages.CreateOrderPage;
import org.example.ui.pages.LoginPage;
import org.example.ui.pages.OrderStatusPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class E2ETest {

    LoginPage loginPage;

    @BeforeEach
    public void openStartPage(){
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void studentMainFlowTest(){
        CreateOrderPage createOrderPage = loginPage.login("useraqa10", "hellouser123");
        createOrderPage.createOrder("Petr", "123456789", "comment");
        createOrderPage.checkOrderCreatedText("Заказ создан!");
        String orderId = createOrderPage.getOrderId();
        OrderStatusPage orderStatusPage = createOrderPage.checkOrderStatusById(orderId);
        orderStatusPage.checkOrderStatus("OPEN");
        orderStatusPage.checkOrderDetails("Petr", "123456789", "comment");
    }
}

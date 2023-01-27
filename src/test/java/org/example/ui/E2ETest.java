package org.example.ui;

import org.example.ui.pages.CreateOrderPage;
import org.example.ui.pages.OrderStatusPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class E2ETest extends BaseTest{

    @Test
    @Tag("e2e")
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

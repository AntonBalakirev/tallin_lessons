package org.example.integration;

import org.example.db.DataBaseManager;
import org.example.rest.api.LoginFunctions;
import org.example.rest.api.TestOrderFunctions;
import org.example.rest.dto.Order;
import org.example.ui.pages.CreateOrderPage;
import org.example.ui.pages.LoginPage;
import org.example.ui.pages.OrderStatusPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class IntegrationTest {

    @Test
    public void withApiIntegrationTest() {
        //API
        //authorization by API
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        LoginFunctions loginFunctions = new LoginFunctions();
        String token = loginFunctions.getToken();
        headers.put("Authorization", "Bearer " + token);

        //create order by API
        Order requestOrder = new Order(
                "OPEN",
                123L,
                "Integration",
                "1234567890",
                "comment"
        );

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();
        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);
        String orderId = String.valueOf(responseOrder.getId());

        //check order details in UI
        LoginPage loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        CreateOrderPage createOrderPage = loginPage.login("useraqa10", "hellouser123");
        OrderStatusPage orderStatusPage = createOrderPage.checkOrderStatusById(orderId);
        orderStatusPage.checkOrderStatus("OPEN");
        orderStatusPage.checkOrderDetails(requestOrder.getCustomerName(), responseOrder.getCustomerPhone(), responseOrder.getComment());
    }

    @Test
    public void withDbIntegrationTest() throws SQLException {
        //create order by UI
        LoginPage loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        CreateOrderPage createOrderPage = loginPage.login("useraqa10", "hellouser123");
        createOrderPage.createOrder("Petr", "123456789", "comment");
        createOrderPage.checkOrderCreatedText("Заказ создан!");
        String orderId = createOrderPage.getOrderId();

        //check order exist in DB
        DataBaseManager dbManager = new DataBaseManager();
        ResultSet resultSet = dbManager.selectOrderById(orderId);
        while (resultSet.next()) {
            Assertions.assertEquals(orderId, resultSet.getString("id"));
            Assertions.assertEquals("OPEN", resultSet.getString("status"));
            Assertions.assertEquals("Petr", resultSet.getString("customer_name"));
            Assertions.assertEquals("123456789", resultSet.getString("customer_phone"));
            Assertions.assertEquals("comment", resultSet.getString("comment"));
        }
    }
}

package org.example.ui;

import org.example.ui.pages.CreateOrderPage;
import org.example.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class OrderTest {

    LoginPage loginPage;

    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void createOrderTest() {
        loginPage
                .login("useraqa10", "hellouser123")
                .createOrder("Pavel", "1234568", "comment")
                .checkOrderCreatedText("Заказ создан!");
    }
}

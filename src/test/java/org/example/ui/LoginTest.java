package org.example.ui;

import org.example.ui.pages.CreateOrderPage;
import org.example.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    LoginPage loginPage;

    @BeforeEach
    public void openStartPage(){
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void loginExceptionTest(){
        loginPage.inputLogin("username");
        loginPage.inputPassword("password");
        loginPage.signIn();
        loginPage.checkIncorrectCredentialsText("Incorrect credentials");
    }

    @Test
    public void loginSuccessfulTest(){
        CreateOrderPage createOrderPage = loginPage.login("useraqa10", "hellouser123");
        createOrderPage.checkPageTitle("Создать заказ");
    }
}

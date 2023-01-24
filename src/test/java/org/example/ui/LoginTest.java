package org.example.ui;

import org.example.ui.pages.CreateOrderPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest{

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

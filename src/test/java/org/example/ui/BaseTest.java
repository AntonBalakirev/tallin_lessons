package org.example.ui;

import org.example.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    LoginPage loginPage;

    @BeforeEach
    public void openStartPage(){
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }
}

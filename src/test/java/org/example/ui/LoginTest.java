package org.example.ui;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    public void loginExceptionTest(){
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user");
        $(By.id("password")).setValue("12345678");
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//*[@data-name='authorizationError-popup-close-button']")).shouldBe(Condition.visible);
        String errorMessage = $(By.xpath("//span[@class='error-popup__title']")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals(
                "Incorrect credentials",
                errorMessage,
                "Сообщение об ошибке отображается некорректно"
        );
    }
}

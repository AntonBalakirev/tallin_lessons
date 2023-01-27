package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "username")
    private SelenideElement loginInput;

    @FindBy(how = How.ID, using = "password")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[@data-name='signIn-button']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@data-name='authorizationError-popup-close-button']")
    private SelenideElement closePopUpButton;

    @FindBy(how = How.XPATH, using = "//span[@class='error-popup__title']")
    private SelenideElement errorPopUpTextField;

    //заполнение поля логин значением
    @Step("Заполенение поля логин значением: {username}")
    public void inputLogin(String username) {
        loginInput.setValue(username);
    }

    //заполнение поля пароль значением
    @Step("Заполенение поля пароль значением: {password}")
    public void inputPassword(String password) {
        passwordInput.setValue(password);
    }

    //нажатие на кнопку логин
    @Step("Нажатие кнопки логин")
    public void signIn(){
        signInButton.click();
    }

    //успешный логин и переход на следующую страницу
    @Step("Авторизация пользователя {username}")
    public CreateOrderPage login(String username, String password){
        inputLogin(username);
        inputPassword(password);
        signIn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Selenide.page(CreateOrderPage.class);
    }

    //проверка сообщения о некорректно введенных логине/пароле
    public void checkIncorrectCredentialsText(String textError){
        closePopUpButton.shouldBe(Condition.visible);
        errorPopUpTextField.shouldHave(Condition.text(textError));
    }
}

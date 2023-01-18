package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateOrderPage {

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = "//input[@name='phone']")
    private SelenideElement phoneInput;

    @FindBy(how = How.XPATH, using = "//input[@name='comment']")
    private SelenideElement commentInput;

    @FindBy(how = How.XPATH, using = "//*[@class='button new-order__button']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = "//h3[@class='notification-popup__text']")
    private SelenideElement popUpTextField;

    @FindBy(how = How.XPATH, using = "//h2[@class='new-order__title']")
    private SelenideElement titleText;

    //Создание заказа
    public CreateOrderPage createOrder(String name, String phone, String comment){
        nameInput.setValue(name);
        phoneInput.setValue(phone);
        commentInput.setValue(comment);
        createOrderButton.click();
        return this;
    }

    //проверка текста сообщения об успешном создании заказа
    public void checkOrderCreatedText(String textSuccess){
        popUpTextField
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(textSuccess));
    }

    //проверка заголовка страницы
    public void checkPageTitle(String title){
        titleText.shouldBe(Condition.visible).shouldHave(Condition.text(title));
    }

}

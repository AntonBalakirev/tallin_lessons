package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateOrderPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//h2[@class='new-order__title']")
    private SelenideElement titleText;

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

    @FindBy(how = How.XPATH, using = "//span[@class='notification-popup__text']")
    private SelenideElement popUpOrderIdField;

    @FindBy(how = How.XPATH, using = "//button[@data-name='orderSuccessfullyCreated-popup-ok-button']")
    private SelenideElement popUpConfirmationButton;

    @FindBy(how = How.XPATH, using = "//button[@data-name='openStatusPopup-button']")
    private SelenideElement openStatusPopUpButton;

    @FindBy(how = How.XPATH, using = "//input[@data-name='searchOrder-input']")
    private SelenideElement searchByOrderIdInput;

    @FindBy(how = How.XPATH, using = "//button[@data-name='searchOrder-submitButton']")
    private SelenideElement searchByOrderIdSubmitButton;

    //проверка заголовка страницы
    public void checkPageTitle(String title) {
        titleText.shouldBe(Condition.visible).shouldHave(Condition.text(title));
    }

    //Создание заказа
    public CreateOrderPage createOrder(String name, String phone, String comment) {
        nameInput.setValue(name);
        phoneInput.setValue(phone);
        commentInput.setValue(comment);
        createOrderButton.click();
        return this;
    }

    //проверка текста сообщения об успешном создании заказа
    public void checkOrderCreatedText(String textSuccess) {
        popUpTextField
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(textSuccess));
    }

    //получение id из статуса заказа
    public String getOrderId() {
        String orderId = popUpOrderIdField.getText().replaceAll("\\D+", "");
        popUpConfirmationButton.click();
        return orderId;
    }

    //проверка статуса заказа по id
    public OrderStatusPage checkOrderStatusById(String orderId) {
        openStatusPopUpButton.click();
        searchByOrderIdInput.setValue(orderId);
        searchByOrderIdSubmitButton.click();
        return Selenide.page(OrderStatusPage.class);
    }
}

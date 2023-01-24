package org.example.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage {

    @FindBy(how = How.XPATH, using = "//a[@data-name='mainPage-link']")
    private SelenideElement logoButton;

    public void clickLogoButton(){
        logoButton.click();
    }

}

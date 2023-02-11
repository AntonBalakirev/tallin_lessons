package org.example.manager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverManager {
    private String driverType;

    public DriverManager() {
        try (InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            driverType = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDriver(){
        switch (driverType){
            case "local":
                Configuration.browser = "chrome";
                break;
            case "remote":
                Configuration.remote = "http://130.193.48.12:4444/wd/hub";
                Configuration.browser = "chrome";
                Configuration.browserSize = "1920x1080";
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;
                break;
        }
    }
}

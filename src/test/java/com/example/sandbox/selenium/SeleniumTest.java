package com.example.sandbox.selenium;


import com.example.sandbox.selenium.util.SeleniumPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.example.sandbox.selenium.util.UtilFields.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {


    private WebDriver webDriver;


    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();

    }

    @Test
    public void testOpenGoogleStartingWebPage() {
        webDriver.get(GOOGLE_URL);

        assertThat(webDriver.getTitle()).isEqualTo(GOOGLE_TITLE);
        assertThat(webDriver.getCurrentUrl()).isEqualToIgnoringCase(GOOGLE_URL);

        webDriver.close();
    }


    @Test
    public void testOpenWikipediaSeleniumPageAndGetTitle() {
        webDriver.get(SELENIUM_SOFTWARE_URL);

        assertThat(webDriver.getTitle()).isEqualTo(SELENIUM_SOFTWARE_TITLE);

        webDriver.close();
    }
    @Test
    public void testOpenWikipediaAndClickOnEdit() {
        webDriver.get(SELENIUM_SOFTWARE_URL);

        SeleniumPage seleniumPage = new SeleniumPage(webDriver);
        seleniumPage.clickOnEditField();

        assertThat(webDriver.getCurrentUrl())
                .isEqualTo(SELENIUM_EDIT);
        webDriver.close();
    }

    @Test
    public void testOpenWikipediaAndTypeToSearch() {

        webDriver.get(SELENIUM_SOFTWARE_URL);

        SeleniumPage seleniumPage = new SeleniumPage(webDriver);
        seleniumPage.searchValue("Selenium");

        assertThat(webDriver.getCurrentUrl()).isEqualTo(SELENIUM_ELEMENT_URL);
        webDriver.close();
    }
}

package com.example.sandbox.selenium;


import com.example.sandbox.selenium.util.SeleniumPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.example.sandbox.selenium.util.UtilFields.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {


    private WebDriver webDriver;


    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test(groups = {"SeleniumTests"})
    public void testOpenGoogleStartingWebPage() {
        webDriver.get(GOOGLE_URL);

        assertThat(webDriver.getTitle()).isEqualTo(GOOGLE_TITLE);
        assertThat(webDriver.getCurrentUrl()).isEqualToIgnoringCase(GOOGLE_URL);

    }



    @Test(groups = {"SeleniumTests"})
    public void testOpenWikipediaSeleniumPageAndGetTitle() {
        webDriver.get(SELENIUM_SOFTWARE_URL);

        assertThat(webDriver.getTitle()).isEqualTo(SELENIUM_SOFTWARE_TITLE);

    }


    @Test(groups = {"SelenideTests"})
    public void testOpenWikipediaAndClickOnEdit() {
        webDriver.get(SELENIUM_SOFTWARE_URL);

        SeleniumPage seleniumPage = new SeleniumPage(webDriver);
        seleniumPage.clickOnEditField();

        assertThat(webDriver.getCurrentUrl())
                .isEqualTo(SELENIUM_EDIT);

    }


    @Test(groups = {"SeleniumTests"})
    public void testOpenWikipediaAndTypeToSearch() {

        webDriver.get(SELENIUM_SOFTWARE_URL);

        SeleniumPage seleniumPage = new SeleniumPage(webDriver);
        seleniumPage.searchValue("Selenium");

        assertThat(webDriver.getCurrentUrl()).isEqualTo(SELENIUM_ELEMENT_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void onTearDown() {
        webDriver.close();
    }
}

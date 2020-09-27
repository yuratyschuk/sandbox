package com.example.sandbox.selenium;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.sandbox.selenium.util.SelenidePage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.example.sandbox.selenium.util.UtilFields.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {


    @Test(groups = {"SelenideTests"})
    public void testOpenGoogleStartingWebPage() {
        open(GOOGLE_URL, SelenidePage.class)
                .shouldHave(GOOGLE_TITLE);

        assertThat(WebDriverRunner.url()).isEqualTo(GOOGLE_URL);

    }


    @Test(groups = {"SelenideTests"})
    public void testOpenWikipediaSeleniumPageAndGetTitle() {
        Selenide.open(SELENIUM_SOFTWARE_URL, SelenidePage.class)
                .shouldHave(SELENIUM_SOFTWARE_TITLE);

    }


    @Test(groups = {"SelenideTests"})
    public void testOpenWikipediaAndClickOnEdit() {
        open(SELENIUM_SOFTWARE_URL, SelenidePage.class)
                .buttonShouldHave("Edit")
                .buttonClick();

        assertThat(WebDriverRunner.url())
                .isEqualTo(SELENIUM_EDIT);
    }


    @Test(groups = {"SelenideTests"})
    public void testOpenWikipediaAndTypeToSearch() {
        open(SELENIUM_SOFTWARE_URL, SelenidePage.class)
                .inputShouldHave("placeholder", WIKIPEDIA_SEARCH)
                .inputSetValue("Selenium")
                .inputSubmit();


        assertThat(WebDriverRunner.url())
                .isEqualTo(SELENIUM_ELEMENT_URL);
    }
}

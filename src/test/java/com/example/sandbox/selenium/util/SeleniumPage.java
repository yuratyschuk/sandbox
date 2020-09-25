package com.example.sandbox.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumPage {

    private final WebDriver webDriver;

    By editFieldLocator = By.id("ca-edit");

    By inputSearchLocator = By.name("search");

    public SeleniumPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnEditField() {
        webDriver.findElement(editFieldLocator).click();

    }

    public void searchValue(String value) {
        WebElement inputSearch = webDriver.findElement(inputSearchLocator);
        inputSearch.sendKeys(value);
        inputSearch.submit();

    }
}

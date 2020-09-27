package com.example.sandbox.selenium.util;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelenidePage {

    private final SelenideElement searchInputWikipedia = $(By.name("search"));

    private final SelenideElement buttonEditWikipedia = $(By.id("ca-edit"));

    private final  SelenideElement title = $("title");


    public void shouldHave(String value) {
        title.shouldHave(Condition.attribute("text", value));

    }

    public void buttonClick() {
        buttonEditWikipedia.click();
    }

    public SelenidePage buttonShouldHave(String value) {
        buttonEditWikipedia.shouldHave(Condition.text(value));

        return this;
    }

    public SelenidePage inputShouldHave(String attribute, String value) {
        searchInputWikipedia.shouldHave(Condition.attribute("placeholder", value));

        return this;
    }

    public SelenidePage inputSetValue(String value) {
        searchInputWikipedia.setValue(value);

        return this;
    }

    public void inputSubmit() {
        searchInputWikipedia.submit();
    }
 }

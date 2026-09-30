package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MealsHistoryPage extends BasePage {
    private final By heading = By.xpath("//h1[normalize-space()='Meals History']");

    public MealsHistoryPage(WebDriver driver) { super(driver); }

    public boolean isLoaded() { return visible(heading).isDisplayed(); }
}
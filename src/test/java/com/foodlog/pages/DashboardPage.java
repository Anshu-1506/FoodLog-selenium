package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By greeting = By.xpath("//h1[contains(normalize-space(),'Good Morning')]");
    private final By streakCard = By.xpath("//h3[normalize-space()='Streak']");

    public DashboardPage(WebDriver driver) { super(driver); }

    public String getGreeting() { return visible(greeting).getText(); }

    public boolean isStreakCardVisible() { return visible(streakCard).isDisplayed(); }
}
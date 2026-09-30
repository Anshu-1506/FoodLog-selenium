package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddMealPage extends BasePage {
    private final By textarea = By.tagName("textarea");
    private final By analyze = By.xpath("//button[contains(normalize-space(),'Analyze Meal')]");
    private final By detectedItems = By.xpath("//h3[normalize-space()='Detected Items']");
    private final By doneButton = By.xpath("//button[contains(normalize-space(),'View Dashboard')]");

    public AddMealPage(WebDriver driver) { super(driver); }

    public AddMealPage open() {
        openPath("/add-meal");
        visible(By.xpath("//h1[normalize-space()='Add Meal']"));
        return this;
    }

    public void selectMealType(String type) {
        click(By.xpath("//button[normalize-space()='" + type + "']"));
    }

    public void analyze(String text) {
        type(textarea, text);
        click(analyze);
    }

    public boolean isPreviewShown() {
        return new WebDriverWait(driver, Duration.ofSeconds(45))
                .until(ExpectedConditions.visibilityOfElementLocated(detectedItems)).isDisplayed();
    }

    public void clickDone() { click(doneButton); }
}
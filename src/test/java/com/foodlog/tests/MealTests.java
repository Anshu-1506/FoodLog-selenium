package com.foodlog.tests;

import com.foodlog.base.BaseTest;
import com.foodlog.pages.AddMealPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealTests extends BaseTest {

    // Calls the real AI endpoint, so the backend needs a valid OpenRouter key
    @Test(groups = "ai", description = "TC15: Meal logging shows preview and returns to dashboard")
    public void addMealShowsPreviewAndReturnsToDashboard() {
        signUpNewUser();
        AddMealPage addMeal = new AddMealPage(driver).open();
        addMeal.selectMealType("lunch");
        addMeal.analyze("2 roti, dal and 1 bowl rice");
        Assert.assertTrue(addMeal.isPreviewShown());
        addMeal.clickDone();
        Assert.assertTrue(addMeal.urlContains("/dashboard"));
    }

    @Test(description = "TC16: Empty meal input shows no preview")
    public void emptyMealInputShowsNoPreview() {
        signUpNewUser();
        AddMealPage addMeal = new AddMealPage(driver).open();
        addMeal.analyze("");
        Assert.assertTrue(driver.findElements(By.xpath("//h3[normalize-space()='Detected Items']")).isEmpty());
    }
}
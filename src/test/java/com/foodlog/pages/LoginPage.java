package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By email = By.cssSelector("input[placeholder='Enter your email']");
    private final By password = By.cssSelector("input[placeholder='Enter your password']");
    private final By submit = By.cssSelector("button[type='submit']");
    private final By error = By.cssSelector("div.text-red-600");
    private final By signupLink = By.linkText("Sign Up");
    private final By heading = By.xpath("//h2[normalize-space()='Welcome back!']");

    public LoginPage(WebDriver driver) { super(driver); }

    public LoginPage open() {
        openPath("/login");
        visible(heading);
        return this;
    }

    public void login(String emailValue, String passwordValue) {
        if (!emailValue.isEmpty()) type(email, emailValue);
        if (!passwordValue.isEmpty()) type(password, passwordValue);
        click(submit);
    }

    public String getErrorText() { return visible(error).getText().trim(); }

    public void goToSignup() { click(signupLink); }
}
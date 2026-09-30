package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {
    private final By name = By.cssSelector("input[placeholder='Enter your name']");
    private final By email = By.cssSelector("input[placeholder='Enter your email']");
    private final By password = By.cssSelector("input[placeholder='Create a password']");
    private final By confirm = By.cssSelector("input[placeholder='Re-enter your password']");
    private final By submit = By.cssSelector("button[type='submit']");
    private final By error = By.cssSelector("div.text-red-600");
    private final By heading = By.xpath("//h2[normalize-space()='Create your account']");

    public SignupPage(WebDriver driver) { super(driver); }

    public SignupPage open() {
        openPath("/signup");
        visible(heading);
        return this;
    }

    public void signup(String n, String e, String p, String c) {
        if (!n.isEmpty()) type(name, n);
        if (!e.isEmpty()) type(email, e);
        if (!p.isEmpty()) type(password, p);
        if (!c.isEmpty()) type(confirm, c);
        click(submit);
    }

    public String getErrorText() { return visible(error).getText().trim(); }
}
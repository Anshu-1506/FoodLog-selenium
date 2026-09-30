package com.foodlog.tests;

import com.foodlog.base.BaseTest;
import com.foodlog.pages.LoginPage;
import com.foodlog.pages.SidebarComponent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "TC06: Valid login redirects to dashboard")
    public void validLoginRedirectsToDashboard() {
        String email = signUpNewUser();
        new SidebarComponent(driver).logout();
        LoginPage login = new LoginPage(driver);
        login.urlContains("/login");
        login.login(email, PASSWORD);
        Assert.assertTrue(login.urlContains("/dashboard"));
    }

    @Test(description = "TC07: Wrong password shows error")
    public void wrongPasswordShowsError() {
        String email = signUpNewUser();
        clearSession();
        LoginPage login = new LoginPage(driver).open();
        login.login(email, "WrongPass@999");
        Assert.assertEquals(login.getErrorText(), "Invalid credentials");
    }

    @Test(description = "TC08: Unregistered email shows error")
    public void unregisteredEmailShowsError() {
        LoginPage login = new LoginPage(driver).open();
        login.login("nobody_" + System.currentTimeMillis() + "@example.com", PASSWORD);
        Assert.assertEquals(login.getErrorText(), "Invalid credentials");
    }

    @Test(description = "TC09: Empty login form shows validation")
    public void emptyFieldsShowError() {
        LoginPage login = new LoginPage(driver).open();
        login.login("", "");
        Assert.assertEquals(login.getErrorText(), "Please fill in both fields.");
    }

    @Test(description = "TC10: Sign Up link opens signup page")
    public void signupLinkNavigatesToSignup() {
        LoginPage login = new LoginPage(driver).open();
        login.goToSignup();
        Assert.assertTrue(login.urlContains("/signup"));
    }
}
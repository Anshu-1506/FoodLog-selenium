package com.foodlog.tests;

import com.foodlog.base.BaseTest;
import com.foodlog.pages.SignupPage;
import com.foodlog.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    @Test(description = "TC01: Valid signup redirects to dashboard")
    public void validSignupRedirectsToDashboard() {
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("QA Tester", Config.uniqueEmail(), PASSWORD, PASSWORD);
        Assert.assertTrue(signup.urlContains("/dashboard"));
    }

    @Test(description = "TC02: Duplicate email is rejected")
    public void duplicateEmailIsRejected() {
        String email = signUpNewUser();
        clearSession();
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("QA Tester", email, PASSWORD, PASSWORD);
        Assert.assertEquals(signup.getErrorText(), "Email already registered");
    }

    @Test(description = "TC03: Mismatched passwords blocked")
    public void mismatchedPasswordsShowError() {
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("QA Tester", Config.uniqueEmail(), PASSWORD, "Different@123");
        Assert.assertEquals(signup.getErrorText(), "Passwords do not match.");
    }

    @Test(description = "TC04: Short password blocked")
    public void shortPasswordShowsError() {
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("QA Tester", Config.uniqueEmail(), "abc", "abc");
        Assert.assertEquals(signup.getErrorText(), "Password must be at least 6 characters.");
    }

    @Test(description = "TC05: Empty fields blocked")
    public void emptyFieldsShowError() {
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("", "", "", "");
        Assert.assertEquals(signup.getErrorText(), "Please fill in all fields.");
    }
}
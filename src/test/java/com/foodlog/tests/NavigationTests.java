package com.foodlog.tests;

import com.foodlog.base.BaseTest;
import com.foodlog.pages.DashboardPage;
import com.foodlog.pages.LoginPage;
import com.foodlog.pages.MealsHistoryPage;
import com.foodlog.pages.SidebarComponent;
import com.foodlog.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTest {

    @Test(description = "TC11: Protected route redirects logged-out user to login")
    public void protectedRouteRedirectsToLogin() {
        driver.get(Config.baseUrl() + "/dashboard");
        Assert.assertTrue(new LoginPage(driver).urlContains("/login"));
    }

    @Test(description = "TC12: Dashboard loads for a new user")
    public void dashboardLoadsForNewUser() {
        signUpNewUser();
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(dashboard.getGreeting().contains("QA"));
        Assert.assertTrue(dashboard.isStreakCardVisible());
    }

    @Test(description = "TC13: Sidebar opens Meals History")
    public void sidebarNavigatesToMealsHistory() {
        signUpNewUser();
        new SidebarComponent(driver).goTo("Meals");
        Assert.assertTrue(new MealsHistoryPage(driver).isLoaded());
        Assert.assertTrue(driver.getCurrentUrl().contains("/meals"));
    }

    @Test(description = "TC14: Logout ends the session")
    public void logoutEndsSession() {
        signUpNewUser();
        new SidebarComponent(driver).logout();
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.urlContains("/login"));
        driver.get(Config.baseUrl() + "/dashboard");
        Assert.assertTrue(login.urlContains("/login"));
    }
}
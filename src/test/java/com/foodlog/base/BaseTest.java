package com.foodlog.base;

import com.foodlog.pages.SignupPage;
import com.foodlog.utils.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final String PASSWORD = "Test@1234";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Config.headless()) options.addArguments("--headless=new");
        options.addArguments("--window-size=1440,900", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.get(Config.baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) saveScreenshot(result.getName());
            driver.quit();
        }
    }

    protected String signUpNewUser() {
        String email = Config.uniqueEmail();
        SignupPage signup = new SignupPage(driver).open();
        signup.signup("QA Tester", email, PASSWORD, PASSWORD);
        signup.urlContains("/dashboard");
        return email;
    }

    protected void clearSession() {
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    private void saveScreenshot(String testName) {
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Files.write(dir.resolve(testName + ".png"), png);
        } catch (IOException ignored) {
        }
    }
}
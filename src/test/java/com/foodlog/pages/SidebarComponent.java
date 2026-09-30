package com.foodlog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidebarComponent extends BasePage {
    public SidebarComponent(WebDriver driver) { super(driver); }

    public void goTo(String label) {
        click(By.xpath("//aside//nav//a[normalize-space()='" + label + "']"));
    }

    public void logout() {
        click(By.xpath("//aside//button[normalize-space()='Log Out']"));
    }
}
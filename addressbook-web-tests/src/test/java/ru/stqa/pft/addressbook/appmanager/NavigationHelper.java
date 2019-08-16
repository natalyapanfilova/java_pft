package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }
}

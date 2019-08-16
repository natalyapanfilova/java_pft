package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }
}

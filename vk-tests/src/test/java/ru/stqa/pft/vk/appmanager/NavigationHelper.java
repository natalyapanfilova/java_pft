package ru.stqa.pft.vk.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoMainPage() {
        if (isElementPresent(By.id("page_load_photo"))) {
            return;
        }
        click(By.id("l_pr"));
    }
}

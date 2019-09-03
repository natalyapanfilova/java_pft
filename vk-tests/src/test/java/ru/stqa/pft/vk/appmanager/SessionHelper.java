package ru.stqa.pft.vk.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String email, String password) {
        type(By.id("index_email"), email);
        type(By.id("index_pass"), password);
        click(By.id("index_login_button"));
    }
}

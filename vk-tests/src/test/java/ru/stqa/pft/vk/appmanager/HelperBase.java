package ru.stqa.pft.vk.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File photo) {
        if (photo != null) {
            wd.findElement(locator).sendKeys(photo.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean isElementVisible(By locator) {
        try {
            if (wd.findElement(locator).getAttribute("style").equals("display: none;")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void selectByText(By locator, By text) {
       click(locator);
       click(text);
    }

    public String getMessage(By locator) {
        System.out.println(wd.findElement(locator).getText());
        return wd.findElement(locator).getText();
    }

    protected String getText(By locator) {
        return wd.findElement(locator).getText();
    }

    protected String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

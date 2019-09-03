package ru.stqa.pft.vk.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private VkHelper vkHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.get("https://vk.com/");
        wd.manage().window().maximize();
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        vkHelper = new VkHelper(wd);
        sessionHelper.login("panfilovatest@yandex.ru", "pas28test");
    }

    public void stop() {
        wd.quit();
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public VkHelper getVkHelper() {
        return vkHelper;
    }

}

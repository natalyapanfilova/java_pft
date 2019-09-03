package ru.stqa.pft.vk.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.vk.model.MainData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VkHelper extends HelperBase {

    public VkHelper(WebDriver wd) {
        super(wd);
    }

    public void initMainDataModification() {
        click(By.id("profile_edit_act"));
    }

    public void fillMainDataForm(MainData mainData) {
        //type(By.id("pedit_first_name"), mainData.getFirstName());
        //type(By.id("pedit_last_name"), mainData.getLastName());
        selectByText(By.xpath("//input[@id='pedit_sex']/.."), By.xpath(String.format("//li[@title='%s']", mainData.getGender())));
        if (mainData.getGender().equals("женский")) {
            type(By.id("pedit_maiden_name"), mainData.getMaiden_name());
        } else {
            Assert.assertFalse(isElementVisible(By.id("pedit_maiden_row")));
        }
        selectByText(By.xpath("//input[@id='pedit_status']/.."),
                By.xpath(String.format("//li[@title='%s']", mainData.getFamilyStatus())));
        String[] dates = mainData.getBirsdayDate().split("\\.");
        selectByText(By.xpath("//input[@id='pedit_bday']/.."), By.xpath(String.format("//li[@title='%s']", dates[0])));
        selectByText(By.xpath("//input[@id='pedit_bmonth']/.."), By.xpath(String.format("//li[@title='%s']", getMonth(dates[1], "text"))));
        selectByText(By.xpath("//input[@id='pedit_byear']/.."), By.xpath(String.format("//li[@title='%s']", dates[2])));
        type(By.id("pedit_home_town"), mainData.getHomeTown());
        addAll(By.id("pedit_add_grandparent_link"), "grandparent", mainData.getGrandmothersAndGrandfathers());
        addAll(By.id("pedit_add_parent_link"), "parent", mainData.getParents());
        addAll(By.id("pedit_add_sibling_link"), "sibling", mainData.getBrothersAndSisters());
        addAll(By.id("pedit_add_child_link"), "child", mainData.getChildren());
        addAll(By.id("pedit_add_grandchild_link"), "grandchild", mainData.getGrandchildren());
    }

    private void addAll(By addLocator, String elementName, String[] array) {
        while (isElementPresent(By.cssSelector("div[id^='pedit_wrap_" + elementName + "']"))) {
            click(By.cssSelector("a[onclick*='removeRelation(\\'" + elementName + "']"));
        }
        for (int l = 0; l < array.length; l++) {
            click(addLocator);
            String elementNumber = "";
            List<WebElement> elements = wd.findElements(By.cssSelector("input[id^='" + elementName + "']"));
            for (WebElement element : elements) {
                if (!element.getAttribute("value").equals("-1")) {
                    elementNumber = element.getAttribute("id");
                }
            }
            By byInLocator = By.xpath("//input[@id='" + elementNumber + "']/..//input[@type='text']");
            click(byInLocator);
            if (array[l] != null) {
                wd.findElement(byInLocator).sendKeys(array[l]);
                wd.findElement(byInLocator).sendKeys(Keys.ENTER);
            }
        }
    }

    private String getMonth(String month, String key) {
        String monthName = "";
        if (key.equals("text")) {
            switch (month) {
                case "01":
                    monthName = "Января";
                    break;
                case "02":
                    monthName = "Февраля";
                    break;
                case "03":
                    monthName = "Марта";
                    break;
                case "04":
                    monthName = "Апреля";
                    break;
                case "05":
                    monthName = "Мая";
                    break;
                case "06":
                    monthName = "Июня";
                    break;
                case "07":
                    monthName = "Июля";
                    break;
                case "08":
                    monthName = "Августа";
                    break;
                case "09":
                    monthName = "Сентября";
                    break;
                case "10":
                    monthName = "Октября";
                    break;
                case "11":
                    monthName = "Ноября";
                    break;
                case "12":
                    monthName = "Декабря";
                    break;
            }
        } else if (key.equals("number")) {
            switch (month) {
                case "января":
                    monthName = "01";
                    break;
                case "февраля":
                    monthName = "02";
                    break;
                case "Марта":
                    monthName = "03";
                    break;
                case "апреля":
                    monthName = "04";
                    break;
                case "мая":
                    monthName = "05";
                    break;
                case "июня":
                    monthName = "06";
                    break;
                case "июля":
                    monthName = "07";
                    break;
                case "августа":
                    monthName = "08";
                    break;
                case "сентября":
                    monthName = "09";
                    break;
                case "октября":
                    monthName = "10";
                    break;
                case "ноября":
                    monthName = "11";
                    break;
                case "декабря":
                    monthName = "12";
                    break;
            }
        } else {
            System.out.println("Incorrect key in method getMonth()");
        }
        return monthName;
    }

    public String getGender() {
        String gender = "";
        try {
            gender = wd.findElement(By.id("pedit_sex")).getAttribute("value");
        } catch (StaleElementReferenceException ex) {
            gender = Integer.toString(1 + (int) (Math.random() * 2));
        }
        if (gender.equals("1")) {
            return "мужской";
        } else return "женский";
    }

    public void submitChanges() {
        click(By.xpath("//button[@class='flat_button button_big_width']"));
    }

    public void showDelailedInf() {
        click(By.linkText("Показать подробную информацию"));
    }

    public MainData getMainData() {
        String bDayText = getText(By.cssSelector("a[href*='bday']")) + " " + getText(By.cssSelector("a[href*='byear']"));
        String[] partsBDay = bDayText.split(" ");
        return new MainData().withBirsdayDate(partsBDay[0] + "." + getMonth(partsBDay[1], "number") + "." + partsBDay[2])
                .withFamilyStatus(firstUpperCase(getText(By.cssSelector("a[href*='status']"))))
                .withHomeTown(getText(By.cssSelector("a[href*='hometown']")))
                .withGrandmothersAndGrandfathers(getText(By.xpath("//div[@id='profile_full']/div[1]/div[2]/div[3]/div[2]"))
                        .split(", "))
                .withParents(getText(By.xpath("//div[@id='profile_full']/div[1]/div[2]/div[4]/div[2]")).split(" и "))
                .withBrothersAndSisters(getText(By.xpath("//div[@id='profile_full']/div[1]/div[2]/div[5]/div[2]"))
                        .split(",  "))
                .withChildren(getText(By.xpath("//div[@id='profile_full']/div[1]/div[2]/div[6]/div[2]")).split("\n"))
                .withGrandchildren(getText(By.xpath("//div[@id='profile_full']/div[1]/div[2]/div[7]/div[2]")).split("\n"));
    }

}

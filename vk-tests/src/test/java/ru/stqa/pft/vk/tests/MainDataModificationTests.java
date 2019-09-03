package ru.stqa.pft.vk.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.vk.model.MainData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MainDataModificationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validMainDataFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        int a;
        int b;
        app.getNavigationHelper().gotoMainPage();
        app.getVkHelper().initMainDataModification();
        String gender = app.getVkHelper().getGender();
        if (gender.equals("женский")) {
            a = 1;
            b =10;
        } else {
            a = 11;
            b = 20;
        }
        int n = a + (int) (Math.random() * b);
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/mainData.csv")))) {
            for (int i = 1; i <= n; i++) {
                String line = reader.readLine();
                if (i == n) {
                    String[] split = line.split(";");
                    list.add(new Object[] {new MainData().withMaiden_name(split[2])
                            .withGender(gender).withFamilyStatus(split[3]).withBirsdayDate(split[4]).withHomeTown(split[5])
                            .withGrandmothersAndGrandfathers(split[6].split(",")).withParents(split[7].split(","))
                            .withBrothersAndSisters(split[8].split(",")).withChildren(split[9].split(","))
                            .withGrandchildren(split[10].split(","))});
                }
            }
            return list.iterator();
        }
    }

    @Test (dataProvider = "validMainDataFromCsv")
    public void testMainDataModification(MainData mainData) {
        app.getVkHelper().fillMainDataForm(mainData);
        app.getVkHelper().submitChanges();
        assertEquals(app.getVkHelper().getMessage(By.xpath("//div[@id='pedit_result']//div[@class='msg_text']")),
                "Изменения сохранены.\n" +
                        "Новые данные будут отражены на Вашей странице.");
        app.getNavigationHelper().gotoMainPage();
        app.getVkHelper().showDelailedInf();
        MainData oldMain = mainData.withMaiden_name(null).withGender(null);
        MainData newMain = app.getVkHelper().getMainData();
        assertEquals(app.getVkHelper().getMainData(), mainData.withMaiden_name(null).withGender(null));
    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(
                new ContactData("Ivanov", "Ivan", "Rostov-on-Don, Siversa 1", "ivanov@mail.ru", "89085555505"));
        app.getContactHelper().submitContactCreation();
    }
}

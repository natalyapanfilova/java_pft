package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(
                new ContactData("Ivanov", "Ivan", "Rostov-on-Don, Siversa 1", "ivanov@mail.ru", "89085555505", "test1"),
                true);
    }
}

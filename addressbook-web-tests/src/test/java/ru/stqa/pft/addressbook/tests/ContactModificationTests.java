package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(
                    new ContactData().withLastName("Ivanov").withFirstName("Ivan").withAddress("Rostov-on-Don, Siversa 1").withEmail("ivanov@mail.ru").withMobilePhone("89085555505").withGroup("test1"),
                    true);
            app.getNavigationHelper().gotoHomePage();
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =
                new ContactData().withId(modifiedContact.getId()).withLastName("Panfilov").withFirstName(modifiedContact.getFirstName()).withAddress(modifiedContact.getAddress()).withEmail(modifiedContact.getEmail()).withMobilePhone("89081023258");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().gotoHomePage();
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(after, before);
    }

}

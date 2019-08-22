package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withLastName("Panfilov").withFirstName(before.get(index).getFirstName()).withAddress(before.get(index).getAddress()).withEmail(before.get(index).getEmail()).withMobilePhone("89081023258");
        app.getContactHelper().modifyContact(index, contact);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}

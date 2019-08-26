package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =
                new ContactData().withId(modifiedContact.getId()).withLastName("Panfilov").withFirstName(modifiedContact.getFirstName()).withAddress(modifiedContact.getAddress()).withEmail(modifiedContact.getEmail()).withMobilePhone("89081023258");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().gotoHomePage();
        assertEquals(app.getContactHelper().count(), before.size());
        Contacts after = app.getContactHelper().all();
        assertThat(after, equalTo(before.without(modifiedContact).withEdded(contact)));
    }

}

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
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().createContact(
                    new ContactData().withLastName("Ivanov").withFirstName("Ivan").inGroup(app.db().groups().iterator().next())
                    , true);
        }
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact =
                new ContactData().withId(modifiedContact.getId()).withLastName("Panfilov").withFirstName(modifiedContact.getFirstName()).withAddress(modifiedContact.getAddress()).withEmail(modifiedContact.getEmail()).withHomePhone("111").withMobilePhone("89085555505").withWorkPhone("333");
        app.getContactHelper().modifyContact(contact);
        app.getNavigationHelper().gotoHomePage();
        assertEquals(app.getContactHelper().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withEdded(contact)));
    }

}

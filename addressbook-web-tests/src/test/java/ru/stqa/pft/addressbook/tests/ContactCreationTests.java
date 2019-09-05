package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/stru.jpg");
        Groups groups = app.db().groups();
        ContactData contact = new ContactData().withLastName("Ivanov").withFirstName("Ivan").withAddress("Rostov-on-Don, Siversa 1")
                .withEmail("ivanov@mail.ru").withHomePhone("111").withMobilePhone("89085555505").inGroup(groups.iterator().next())
                .withPhoto(photo);
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        assertThat(app.getContactHelper().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withEdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}

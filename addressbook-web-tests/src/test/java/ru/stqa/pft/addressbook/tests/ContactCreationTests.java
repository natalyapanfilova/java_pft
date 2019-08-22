package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        String group = ensurePreconditions();
        Set<ContactData> before = app.getContactHelper().all();
        ContactData contact = new ContactData().withLastName("Ivanov").withFirstName("Ivan").withAddress("Rostov-on-Don, Siversa 1").withEmail("ivanov@mail.ru").withMobilePhone("89085555505").withGroup(group);
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(after, before);
    }

    private String ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        String groupName = "test1";
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData().withName(groupName).withHeader("test2").withFooter("test3"));
        } else {
            groupName = app.getGroupHelper().getGroupName();
        }
        app.getNavigationHelper().gotoHomePage();
        return groupName;
    }
}

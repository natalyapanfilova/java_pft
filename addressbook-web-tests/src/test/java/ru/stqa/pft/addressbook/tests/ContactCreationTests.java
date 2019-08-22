package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        String group = ensurePreconditions();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData().withLastName("Ivanov").withFirstName("Ivan").withAddress("Rostov-on-Don, Siversa 1").withEmail("ivanov@mail.ru").withMobilePhone("89085555505").withGroup(group);
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId )).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparing(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
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

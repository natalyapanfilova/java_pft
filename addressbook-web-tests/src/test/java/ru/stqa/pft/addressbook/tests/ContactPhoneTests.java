package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.getContactHelper().createContact(
                    new ContactData().withLastName("Ivanov").withFirstName("Ivan").withHomePhone("111").withMobilePhone("89085555505")
                            .withWorkPhone("333").inGroup(app.db().groups().iterator().next()), true);
        }
        app.getNavigationHelper().gotoHomePage();
    }


    @Test
    public void testContactPhones() {
        ContactData contact = app.getContactHelper().all().iterator().next();
        ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleanedPhones).collect(Collectors.joining("\n"));
    }

    private static String cleanedPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}

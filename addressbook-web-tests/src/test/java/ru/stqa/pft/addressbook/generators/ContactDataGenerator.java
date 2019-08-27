package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String args[]) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getLastName(), contact.getFirstName(), contact.getAddress(),
                    contact.getEmail(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withLastName(String.format("lastName %s", i))
                    .withFirstName(String.format("firstName %s", i)).withAddress(String.format("address %s", i))
                    .withEmail(String.format("email%s@mail.ru", i)).withHomePhone(String.format("111%s", i))
                    .withMobilePhone(String.format("222%s", i)).withWorkPhone(String.format("333%s", i)));
        }
        return contacts;
    }
}

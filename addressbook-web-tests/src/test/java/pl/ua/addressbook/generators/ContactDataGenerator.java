package pl.ua.addressbook.generators;

import pl.ua.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getNickname(),
              contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone(), contact.getEmail(), contact.getAddress()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("test  %s", i))
              .withLastname(String.format("lastname  %s", i)).withNickname(String.format("nikname  %s", i))
              .withHomephone(String.format("111 %s", i)).withMobilephone(String.format("222 %s", i))
              .withWorkphone(String.format("333 %s", i)).withEmail(String.format("test%s.test@test.com", i))
              .withAddress(String.format("%s Oakland Ave, 7%s, Florida, 3210$ ", i, i)));
    }
    return contacts;
  }
}

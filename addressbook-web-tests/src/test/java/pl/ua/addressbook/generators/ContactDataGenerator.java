package pl.ua.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {

    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
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

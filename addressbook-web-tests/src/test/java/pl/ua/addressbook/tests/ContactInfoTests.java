package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("fullContact").withLastname("kostitsyna").withNickname("yk2")
              .withHomephone("111").withMobilephone("222").withWorkphone("333")
              .withAddress("50-513 Warsaw, Chmelna 45-46")
              .withEmail("test.test@test.ua").withEmail2("test2.test@test.ua").withEmail3("test3.test@test.ua"));
    }
  }

  @Test
  public void testContactInfo() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactFromEditForm)));
    assertThat(contact.getAddress(), equalTo(cleanedAddress(contactFromEditForm.getAddress())));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone()).stream().filter((s) -> !s.equals("")).map(ContactInfoTests::cleanedPhone).collect(Collectors.joining("\n"));
  }

  public static String cleanedPhone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedAddress(String address) {
    return address.replaceAll(" \n", "\n");
  }
}
package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;

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

    assertThat(contact.getHomephone(), equalTo(cleanedphone(contactFromEditForm.getHomephone())));
    assertThat(contact.getMobilephone(), equalTo(cleanedphone(contactFromEditForm.getMobilephone())));
    assertThat(contact.getWorkphone(), equalTo(cleanedphone(contactFromEditForm.getWorkphone())));

  }

  public String cleanedphone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}

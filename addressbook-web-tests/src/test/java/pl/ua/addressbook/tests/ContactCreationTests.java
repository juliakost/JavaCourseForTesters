package pl.ua.addressbook.tests;

import org.testng.annotations.*;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("newContact").withLastname("kostitsyna88").withNickname("yk2").withHomephone("111").withMobilephone("222").withWorkphone("333")
            .withAddress("50-513 Warsaw, Chmelna 45-46")
            .withEmail("test.test@test.ua").withEmail2("test2.test@test.ua").withEmail3("test3.test@test.ua");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
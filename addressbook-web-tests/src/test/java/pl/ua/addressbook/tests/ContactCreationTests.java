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
            withFirstname("newContact").withLastname("kostitsyna88").withNickname("yk2");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo (before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
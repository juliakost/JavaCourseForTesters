package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("modifyContact").withLastname("kostitsyna88").withNickname("yk2").withHomephone("111").withMobilephone("222").withWorkphone("333")
              .withAddress("50-513 Warsaw, Chmelna 45-46")
              .withEmail("test.test@test.ua").withEmail2("test2.test@test.ua").withEmail3("test3.test@test.ua"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstname("modification").withLastname("kostitsyna88").withNickname("yk6").withHomephone("111").withMobilephone("222").withWorkphone("333")
            .withAddress("50-513 Warsaw, Chmelna 45-46")
            .withEmail("test.test@test.ua").withEmail2("test2.test@test.ua").withEmail3("test3.test@test.ua");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
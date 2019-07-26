package pl.ua.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("deleteContact").withLastname("kostitsyna88").withNickname("yk2"));
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Thread.sleep(5000);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
  }
}
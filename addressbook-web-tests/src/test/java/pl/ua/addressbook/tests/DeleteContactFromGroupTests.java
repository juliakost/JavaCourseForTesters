package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.Contacts;
import pl.ua.addressbook.model.GroupData;
import pl.ua.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.ua.addressbook.tests.TestBase.app;

public class DeleteContactFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/cat.png");
      app.contact().create(new ContactData().
              withFirstname("addContactToGroup").withLastname("lastname_addContactToGroup")
              .withNickname("nick_addToGroup").withPhoto(photo));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData()
              .withName("groupToAddContact").withFooter("groupToAddContact_footer").withHeader("groupToAddContact_header"));
    }
  }

  @Test
  public void testContactDeleteFromGroup() {
    app.goTo().homePage();
    Contacts contacts = app.db().contacts();
    ContactData contactFromGroup = contacts.iterator().next();
    Groups contactGroupsBefore = contactFromGroup.getGroups();
    if (contactGroupsBefore.size() == 0) {
      Groups groups = app.db().groups();
      GroupData groupToAdd = groups.iterator().next();
      app.contact().addToGroup(contactFromGroup, groupToAdd);
    }
    Contacts contacts1 = app.db().contacts();
    ContactData contactFromGroup1 = contacts1.iterator().next();
    Groups contactGroupsBefore1 = contactFromGroup1.getGroups();
    GroupData group = contactGroupsBefore1.iterator().next();
    app.goTo().homePage();
    app.contact().removeFromGroup(contactFromGroup, group);

    Contacts contacts2 = app.db().contacts();
    ContactData contactFromGroup2 = contacts2.iterator().next();

    Groups contactGroupsAfter = contactFromGroup2.getGroups();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore1.without(group)));

  }
}

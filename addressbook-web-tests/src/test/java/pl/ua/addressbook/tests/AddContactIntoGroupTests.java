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


public class AddContactIntoGroupTests extends TestBase {
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
  public void testContactAddToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contactToGroup = contacts.iterator().next();
    GroupData groupToAdd = groups.iterator().next();
    Groups contactGroupsBefore = contactToGroup.getGroups();
    Contacts groupContacts = groupToAdd.getContacts();
    if (groupContacts.contains(contactToGroup)) {
      for (GroupData group : groups.without(groupToAdd)) {
        Contacts contactsInGroup = group.getContacts();
        if (!contactsInGroup.contains(contactToGroup)) {
          groupToAdd = group;
        }
      }
      app.goTo().groupPage();
      app.group().create(new GroupData()
              .withName("groupToAddContact2").withFooter("groupToAddContact_footer2").withHeader("groupToAddContact_header2"));
      for (GroupData groupNew : app.db().groups()) {
        if (groupNew.getName().equals("groupToAddContact2")) {
          groupToAdd = groupNew;
        }
      }
    }

    app.goTo().homePage();
    app.contact().addToGroup(contactToGroup, groupToAdd);
    Contacts contacts1 = app.db().contacts();
    ContactData contactToGroup1 = contacts1.iterator().next();

    Groups contactGroupsAfter = contactToGroup1.getGroups();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupToAdd)));
  }
}

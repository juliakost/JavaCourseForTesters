package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.GroupData;

import java.io.File;

import static pl.ua.addressbook.tests.TestBase.app;

public class DeleteContactFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/cat.png");
      app.contact().create(new ContactData().
              withFirstname("deleteContactfromGroup").withLastname("lastname_deleteFromGroup")
              .withNickname("nick_deleteFromGroup").withPhoto(photo));
    }
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData()
              .withName("delete group name").withFooter("delete footer").withHeader("delete header"));
    }
  }
}

package pl.ua.addressbook.tests;

import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testCroupModification() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("final group2", "header3", "footer3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "foter"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}

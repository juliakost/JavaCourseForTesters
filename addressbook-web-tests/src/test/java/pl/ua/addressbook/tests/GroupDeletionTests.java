package pl.ua.addressbook.tests;

import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testCroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("final group2", "header3", "footer3"));
    }

    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}

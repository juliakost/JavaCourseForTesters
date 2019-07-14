package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testCroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("final group2", "header3", "footer3"));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() -1);
  }
}

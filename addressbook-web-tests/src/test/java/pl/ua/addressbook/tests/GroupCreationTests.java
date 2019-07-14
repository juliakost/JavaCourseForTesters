package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    int before = app.getGroupHelper().getGroupCpiunt();
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("final group2", "header3", "footer3"));
    int after = app.getGroupHelper().getGroupCpiunt();
    Assert.assertEquals(after, before +1);
  }
}

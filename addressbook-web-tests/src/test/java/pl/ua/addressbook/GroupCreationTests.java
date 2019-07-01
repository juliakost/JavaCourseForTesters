package pl.ua.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test3", "header3", "footer3"));
    submitGroupCreation();
    returnToGroupPage();
  }
}

package pl.ua.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getContractHelper().selectContract();
    app.getContractHelper().initContractDeletion();
    app.getContractHelper().confirmOnAlert();
  }
}
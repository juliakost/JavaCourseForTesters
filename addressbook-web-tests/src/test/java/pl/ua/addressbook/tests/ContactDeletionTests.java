package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws InterruptedException {
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("final2", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));

    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContract(before.size() - 1);
    app.getContactHelper().initContractDeletion();
    app.getContactHelper().confirmOnAlert();
    Thread.sleep(5000);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
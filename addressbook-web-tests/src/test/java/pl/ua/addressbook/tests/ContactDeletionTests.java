package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContractData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws InterruptedException {
    if (!app.getContractHelper().isContactPresent()) {
      app.getContractHelper().createContact(new ContractData("final2", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));

    }
    int before = app.getContractHelper().getContactCount();
    app.getContractHelper().selectContract( before - 1);
    app.getContractHelper().initContractDeletion();
    app.getContractHelper().confirmOnAlert();
    Thread.sleep(2000);
    int after = app.getContractHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
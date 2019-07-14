package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContractData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    int before = app.getContractHelper().getContactCount();
    if (!app.getContractHelper().isContactPresent()) {
      app.getContractHelper().createContact(new ContractData("final2", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));

    }
    app.getContractHelper().selectContract(before -1 );
    app.getContractHelper().initContractModification();
    app.getContractHelper().fillContactForm(new ContractData("modification", "kostitsyna345", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));
    app.getContractHelper().submitContractModification();
    app.getContractHelper().returnToHomePage();
    int after = app.getContractHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
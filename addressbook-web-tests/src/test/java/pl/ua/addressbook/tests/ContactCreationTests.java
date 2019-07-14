package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.ua.addressbook.model.ContractData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContractHelper().getContactCount();
    app.getContractHelper().createContact(new ContractData("fina77", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));
    int after = app.getContractHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
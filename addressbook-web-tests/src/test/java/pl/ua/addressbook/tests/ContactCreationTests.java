package pl.ua.addressbook.tests;

import org.testng.annotations.*;
import pl.ua.addressbook.model.ContractData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContractHelper().initContactCreation();
    app.getContractHelper().fillContactForm(new ContractData("se99rg777", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September", "test3"));
    app.getContractHelper().submitContractCreation();
    app.getContractHelper().returnToHomePage();
  }
}

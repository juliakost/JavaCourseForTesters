package pl.ua.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContractData("serg777", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September", "test3"));
    submitContractCreation();
    returnToHomePage();
  }
}

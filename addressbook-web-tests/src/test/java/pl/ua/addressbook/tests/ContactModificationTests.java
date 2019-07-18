package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("final2", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September"));

    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContract(before.size() -1 );
    app.getContactHelper().initContractModification(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"modification", "kostitsyna88", "yk2", "PL2", "Walonska 92", "123452", "543212", "67892", "98762", "juliakost22008@gmail.com", "1975", "7", "September");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContractModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
package pl.ua.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.ua.addressbook.model.ContactData;
import pl.ua.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContractCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContract(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContractById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContractModificationById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();

  }

  public void submitContractModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void initContractDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmOnAlert() {
    wd.switchTo().alert().accept();
  }

  public boolean isContactPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contract) {
    initContactCreation();
    fillContactForm(contract);
    submitContractCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContractById(contact.getId());
    initContractModificationById(contact.getId());
    fillContactForm(contact);
    submitContractModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContractById(contact.getId());
    initContractDeletion();
    confirmOnAlert();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails)
              .withAddress(address));
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContractModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();

    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomephone(home).withMobilephone(mobile).withWorkphone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
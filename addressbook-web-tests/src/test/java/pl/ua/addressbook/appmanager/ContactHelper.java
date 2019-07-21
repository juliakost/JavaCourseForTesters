package pl.ua.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.ua.addressbook.model.ContactData;

import java.util.ArrayList;
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
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void modify(List<ContactData> before, ContactData contact) {
    selectContract(before.size() - 1);
    initContractModification(before.size() - 1);
    fillContactForm(contact);
    submitContractModification();
    returnToHomePage();
  }

  public void selectContract(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContractModification(int i) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(i).click();

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
    returnToHomePage();
  }

  public void delete(int index) {
    selectContract(index);
    initContractDeletion();
    confirmOnAlert();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}


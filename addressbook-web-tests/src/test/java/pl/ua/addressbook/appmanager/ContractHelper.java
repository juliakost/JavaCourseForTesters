package pl.ua.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pl.ua.addressbook.model.ContractData;

public class ContractHelper extends HelperBase {

  public ContractHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContractCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContractData contractData) {
    type(By.name("firstname"), contractData.getFirstname());
    type(By.name("lastname"), contractData.getLastname());
    type(By.name("nickname"), contractData.getNickname());
    type(By.name("company"), contractData.getCompany());
    type(By.name("address"), contractData.getAddress());
    type(By.name("home"), contractData.getHomephone());
    type(By.name("mobile"), contractData.getMobilephone());
    type(By.name("work"), contractData.getWorkphone());
    type(By.name("fax"), contractData.getFax());
    type(By.name("email"), contractData.getEmail());
    select(By.name("bday"), contractData.getBday());
    select(By.name("bmonth"), contractData.getBmonth());
    type(By.name("byear"), contractData.getByear());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void selectContract() {
    click(By.name("selected[]"));
  }

  public void initContractModification() {
    click(By.cssSelector("img[alt=\"Edit\"]"));

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

  public void createContact(ContractData contract) {
    initContactCreation();
    fillContactForm(contract);
    submitContractCreation();
    returnToHomePage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}


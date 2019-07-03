package pl.ua.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pl.ua.addressbook.model.ContractData;

public class ContractHelper {
  private WebDriver wd;

  public ContractHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void submitContractCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillContactForm(ContractData contractData) {
    wd.findElement(By.name("firstname")).sendKeys(contractData.getFirstname());
    wd.findElement(By.name("lastname")).sendKeys(contractData.getLastname());
    wd.findElement(By.name("nickname")).sendKeys(contractData.getNickname());
    wd.findElement(By.name("company")).sendKeys(contractData.getCompany());
    wd.findElement(By.name("address")).sendKeys(contractData.getAddress());
    wd.findElement(By.name("home")).sendKeys(contractData.getHomephone());
    wd.findElement(By.name("mobile")).sendKeys(contractData.getMobilephone());
    wd.findElement(By.name("work")).sendKeys(contractData.getWorkphone());
    wd.findElement(By.name("fax")).sendKeys(contractData.getFax());
    wd.findElement(By.name("email")).sendKeys(contractData.getEmail());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contractData.getBday());
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contractData.getBmonth());
    wd.findElement(By.name("byear")).sendKeys(contractData.getByear());
    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contractData.getGroup());
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }
}

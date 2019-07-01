package pl.ua.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  protected void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  protected void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  protected void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  protected void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  protected void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  protected void submitContractCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  protected void fillContactForm(ContractData contractData) {
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

  protected void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}

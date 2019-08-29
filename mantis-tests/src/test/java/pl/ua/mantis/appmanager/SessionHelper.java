package pl.ua.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {

    super(app);
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    click(By.xpath("//input[@value='Login']"));
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Login']"));
  }
}

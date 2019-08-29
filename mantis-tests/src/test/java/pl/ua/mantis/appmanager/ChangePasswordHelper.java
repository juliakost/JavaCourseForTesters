package pl.ua.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {
  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }


  public void resetPassword(String username) {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    click(By.linkText(username));
    click(By.cssSelector("input[value = 'Reset Password']"));
  }

  public void finish(String changeLink, String password) {
    wd.get(changeLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type = 'submit']"));
  }
}

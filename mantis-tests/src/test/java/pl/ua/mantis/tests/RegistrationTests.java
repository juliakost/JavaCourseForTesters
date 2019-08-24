package pl.ua.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.domain");
  }

  @AfterMethod(alwaysRun = true)
  public void stoptMailServer() {
    app.mail().stop();

  }
}

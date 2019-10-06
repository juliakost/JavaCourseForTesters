package pl.ua.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    String user = "user1";

    app.mail().drainEmail(user, app.db().getUserPassword(user));
    String newPassword = "password";
    app.sessionHelper().login("administrator", "root");
    app.changePass().resetPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForEmail(1, 10000);
    String changeLink = findChangeLink(mailMessages, app.db().getUserEmail(user));
    System.out.println(changeLink);
    app.changePass().finish(changeLink, newPassword);
    assertTrue(app.newSession().login(user, newPassword));
  }

  private String findChangeLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

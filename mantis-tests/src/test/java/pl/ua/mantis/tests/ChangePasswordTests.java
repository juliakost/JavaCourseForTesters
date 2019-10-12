package pl.ua.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.mantis.model.MailMessage;
import pl.ua.mantis.model.User;
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
    String user = app.dbHelper().usersData().get(0).getUsername();
    int numberUsers = app.dbHelper().usersData().size();
    if(user.equals("administrator") && numberUsers == 1){
      System.out.println( "There is no User other than Admin. New User will be added");
      app.registration().start("non-admin","non-admin@localhost.domain");
    }
    user = app.dbHelper().usersData().get(1).getUsername();
    app.mail().drainEmail(user, app.db().getUserPassword(user));
    String newPassword = "passwordNew";
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

package pl.ua.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.ua.mantis.appmanager.ApplicationManager;
import pl.ua.mantis.model.Issue;
import pl.ua.mantis.model.IssueJson;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"),
//            "config_inc.php", "config_inc.php.bak");
  }


  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    Issue issue = app.soap().getIssue(issueId);
    if (issue.getStatus().equals("closed")) {
      return false;
    }
    return true;
  }

  public boolean isIssueOpenBugify(int issueIdJson) throws IOException {
    IssueJson issuejson = app.rest().getIssueBugify(issueIdJson);
    if (issuejson.getStatus().equals("closed")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public void skipIfNotFixedBugify(int issueIdJson) throws IOException {
    if (isIssueOpenBugify(issueIdJson)) {
      throw new SkipException("Ignored because of issue " + issueIdJson);
    }
  }

  @AfterSuite
  public void tearDown() throws Exception {
    //app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }
}
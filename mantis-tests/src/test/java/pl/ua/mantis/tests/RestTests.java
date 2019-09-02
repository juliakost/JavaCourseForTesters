package pl.ua.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.mantis.model.IssueJson;

import java.io.IOException;
import java.util.Set;

public class RestTests extends TestBase {

  @Test

  public void testCreateIssue() throws IOException {
    Set<IssueJson> oldIssues = app.rest().getIssues();
    IssueJson newIssue = new IssueJson().withSubject("Test issue").withDescription("New test issue");
    int issueId = app.rest().createIssue(newIssue);
    Set<IssueJson> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }
}
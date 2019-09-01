package pl.ua.mantis.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.ua.mantis.model.IssueJson;

import java.io.IOException;
import java.util.Set;

public class RestTests {

  @Test

  public void testCreateIssue() throws IOException {
    Set<IssueJson> oldIssues = getIssues();
    IssueJson newIssue = new IssueJson().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<IssueJson> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

  private Set<IssueJson> getIssues() throws IOException {
    String json  = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");

    return new Gson().fromJson(issues, (new TypeToken<Set<IssueJson>>() {
    }).getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");

  }

  private int createIssue(IssueJson newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
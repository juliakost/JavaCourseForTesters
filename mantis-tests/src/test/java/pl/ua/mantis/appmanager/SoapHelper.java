package pl.ua.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import pl.ua.mantis.model.Issue;
import pl.ua.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p) -> new Project()
            .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator().getMantisConnectPort
            (new URL("http://localhost/mantisbt-2.21.2/mantisbt-2.21.2/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root",
            BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSammary());
    issueData.setDescription(issue.getDescription());
    issueData.setCategory(categories[0]);
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue()).withSammary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getId().intValue())
                    .withName(createdIssueData.getProject().getName()));

  }

  public Issue getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issueInfo = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    return new Issue().withId(issueInfo.getId().intValue()).withSammary(issueInfo.getSummary())
            .withDescription(issueInfo.getDescription())
            .withProject(new Project().withId(issueInfo.getId().intValue())
                    .withName(issueInfo.getProject().getName())).withStatus(issueInfo.getStatus().getName());
  }
}


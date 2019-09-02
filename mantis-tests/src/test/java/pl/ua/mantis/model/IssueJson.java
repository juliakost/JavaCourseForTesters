package pl.ua.mantis.model;

import java.util.Objects;

public class IssueJson {
  private int id;
  private String subject;
  private String description;
  private String status;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IssueJson issueJson = (IssueJson) o;
    return id == issueJson.id &&
            Objects.equals(subject, issueJson.subject) &&
            Objects.equals(description, issueJson.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, description);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public IssueJson withId(int id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public IssueJson withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public IssueJson withDescription(String description) {
    this.description = description;
    return this;
  }
}

package pl.ua.mantis.model;

public class Issue {
  private int id;
  private String summary;
  private String description;
  private Project project;
  private String status;

  public String getStatus() {
    return status;
  }

  public Issue withStatus(String status) {
    this.status = status;
    return this;
  }

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSammary() {
    return summary;
  }

  public Issue withSammary(String sammary) {
    this.summary = sammary;
    return this;
  }


  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }


  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;

  }
}

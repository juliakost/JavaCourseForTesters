package pl.ua.mantis.model;

public class Project {
  private int id;
  private String name;

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }
}

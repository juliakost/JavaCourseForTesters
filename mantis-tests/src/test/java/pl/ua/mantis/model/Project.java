package pl.ua.mantis.model;

public class Project {
  private int id;

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;

  }

  private String name;

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }
}

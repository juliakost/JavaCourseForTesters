package pl.ua.mantis.appmanager;

import pl.ua.mantis.model.UserData;

public class UserDataHelper {

  private ApplicationManager app;

  public UserDataHelper(ApplicationManager app) {
    this.app = app;
  }

  public String getUserPassword(String name) {
    String pass = null;
    for (UserData userdata : app.dbHelper().usersData()) {
      if (userdata.getUsername().equals(name)) {
        pass = userdata.getPassword();
        break;
      }
    }
    return pass;
  }

  public String getUserEmail(String name) {
    String email = null;
    for (UserData userdata : app.dbHelper().usersData()) {
      if (userdata.getUsername().equals(name)) {
        email = userdata.getEmail();
        break;
      }
    }
    return email;
  }
}

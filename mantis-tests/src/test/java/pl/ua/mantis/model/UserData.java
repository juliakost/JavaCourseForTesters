package pl.ua.mantis.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")

public class UserData {
  public String username;
  public String password;
  public String email;

  public UserData(String username, String password, String email){
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData withPassowrd (String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail (String email) {
    this.email = email;
    return this;
  }
}

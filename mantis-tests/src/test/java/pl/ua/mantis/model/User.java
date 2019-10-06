package pl.ua.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")

public class User {
  @Id
  @Column(name= "id")
  private int id;

  @Column(name = "username")
  public String username;
  @Column(name = "password")
  public String password;
  @Column(name = "email")
  public String email;


    public User(){}


  public User(String username, String password, String email){
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User withPassowrd (String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User withEmail (String email) {
    this.email = email;
    return this;
  }
}

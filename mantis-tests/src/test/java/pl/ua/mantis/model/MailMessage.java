package pl.ua.mantis.model;

import javax.mail.internet.MimeMessage;

public class MailMessage {
  public String to;
  public String text;

  public MailMessage (String to, String text){
    this.to = to;
    this.text = text;
  }
}

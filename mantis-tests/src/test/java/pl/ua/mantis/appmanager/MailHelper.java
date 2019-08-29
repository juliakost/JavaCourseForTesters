package pl.ua.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import pl.ua.mantis.model.MailMessage;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
  private ApplicationManager app;
  private Store store;
  private final Wiser wiser;

  public MailHelper (ApplicationManager app){
    this.app = app;
    wiser = new Wiser();
  }

  public List<MailMessage> waitForEmail (int count, long timeout) throws MessagingException, IOException {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout){
      if(wiser.getMessages().size() >=count){
        return wiser.getMessages().stream().map((m)-> toModelMail(m)).collect(Collectors.toList());
      }
      try{
        Thread.sleep(1000);
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    throw new Error("No mail  :(");
  }

  public static MailMessage toModelMail (WiserMessage m){
    try{
      MimeMessage mm = m.getMimeMessage();
      return new MailMessage (mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    }catch (MessagingException e){
      e.printStackTrace();
      return null;
    }catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

  public void drainEmail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username,password);
    for (Message message: inbox.getMessages()){
      message.setFlag(Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }

  private Folder openInbox(String username, String password) throws MessagingException {
    store.connect(username, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  public void start() {wiser.start();}

  public void stop() {wiser.stop();}

}

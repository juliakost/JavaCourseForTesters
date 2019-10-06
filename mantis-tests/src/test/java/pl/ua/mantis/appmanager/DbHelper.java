package pl.ua.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.ua.mantis.model.User;

import java.util.List;

public class DbHelper {

  private SessionFactory sessionFactory;
  //private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    //this.app=app;
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }


  public List<User> usersData() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> result = session.createQuery("from User").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}

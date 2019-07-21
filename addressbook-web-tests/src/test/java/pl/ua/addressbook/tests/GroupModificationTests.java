package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData()
              .withName("modify group").withFooter("modify footer").withHeader("modify header"));
    }
  }

  @Test
  public void testCroupModification() {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData().
            withId(before.get(before.size() - 1).getId()).withName(" change name").withHeader("change heder").withFooter("change foter");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

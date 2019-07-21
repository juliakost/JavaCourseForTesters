package pl.ua.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData()
              .withName("modify group").withFooter("modify footer").withHeader("modify header"));
    }
  }

  @Test
  public void testCroupModification() {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("change name").withHeader("change heder").withFooter("change foter");
    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }
}

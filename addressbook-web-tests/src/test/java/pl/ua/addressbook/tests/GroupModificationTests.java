package pl.ua.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;
import pl.ua.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

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

    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId()).withName("change name").withHeader("change heder").withFooter("change foter");
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), equalTo(before.size()));

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}

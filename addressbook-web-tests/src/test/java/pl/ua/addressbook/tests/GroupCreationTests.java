package pl.ua.addressbook.tests;

import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;
import pl.ua.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.AssertJUnit.assertEquals;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("new group").withFooter("new footer").withHeader("new header");
    app.group().create(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

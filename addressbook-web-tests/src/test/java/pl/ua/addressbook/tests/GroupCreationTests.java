package pl.ua.addressbook.tests;

import org.testng.annotations.Test;
import pl.ua.addressbook.model.GroupData;
import pl.ua.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;



public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("new group").withFooter("new footer").withHeader("new header");
    app.group().create(group);
    assertThat(app.group().count(), equalTo (before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

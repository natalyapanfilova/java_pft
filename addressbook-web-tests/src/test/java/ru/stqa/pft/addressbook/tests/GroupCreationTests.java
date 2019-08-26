package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        Groups before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.getGroupHelper().createGroup(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size() + 1));
        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        Groups before = app.getGroupHelper().all();
        GroupData group = new GroupData().withName("test1'");
        app.getGroupHelper().createGroup(group);
        assertThat(app.getGroupHelper().count(), equalTo(before.size()));
        Groups after = app.getGroupHelper().all();
        assertThat(after, equalTo(before));
    }

}

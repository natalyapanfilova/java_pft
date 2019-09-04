package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromCsv() throws IOException {
            List<Object[]> list = new ArrayList<>();
            list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
            list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
            list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] split = line.split(";");
                    list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
                    line = reader.readLine();
                }
                return list.iterator();
            }
        }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        app.getNavigationHelper().gotoGroupPage();
        Groups before = app.getGroupHelper().all();
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

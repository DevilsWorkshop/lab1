package sandro;

import org.junit.Test;
import sandro.service.UserService;
import sandro.service.SourceService;
import sandro.entity.User;
import sandro.entity.Source;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class SourceServiceTest {

    SourceService sourceService = new SourceService();
    UserService userService = new UserService();


    @Test
    public void testSaveRecord() throws Exception {

        Source source = new Source();
        source.setName("ain");
        source.setUrl("http://ain.ua");
        User user = new User();
        user.setEmail("chupak@gmail.comj");
        user.setLogin("chupak");
        user.setPassword("kapa");
        User userFromDB = userService.add(user);
        source.setUser(userFromDB);
        Source sourceFromDB = sourceService.add(source);
        assertThat(sourceFromDB).isNotNull();
        sourceService.delete(sourceFromDB.getId());
        userService.delete(userFromDB.getId());
        
        
    }

    @Test
    public void testDeleteRecord() throws Exception {
        Source source = new Source();
        source.setName("ain");
        source.setUrl("http://ain.ua");
        User user = new User();
        user.setEmail("izya@mail.com");
        user.setLogin("izya");
        user.setPassword("izya");
        User userFromDB = userService.add(user);
        source.setUser(userFromDB);
        Source sourceFromDB = sourceService.add(source);
        int id = sourceFromDB.getId();
        sourceService.delete(id);
        assertThat(sourceService.get(id)).isNull();
        userService.delete(userFromDB.getId());
    }

    @Test
    public void testUpdate() throws Exception {

        Source source = new Source();
        source.setName("dou");
        source.setUrl("http://dou.ru");
        User user = new User();
        user.setEmail("informatio@gmail.com");
        user.setLogin("bronf");
        user.setPassword("man");
        User userFromDB = userService.add(user);
        source.setUser(userFromDB);
        source = sourceService.add(source);
        source.setUrl("http://dou.ua");
        sourceService.update(source);
        Source sourceFromDB = sourceService.get(source.getId());
        assertThat(sourceFromDB).isEqualTo(source);
        assertThat(sourceFromDB.getUrl()).isEqualTo("http://dou.ua");
        sourceService.delete(sourceFromDB.getId());
        userService.delete(userFromDB.getId());
    }

@Test
    public void testGetAll() {

        Source source0 = new Source();
        source0.setName("dvach0");
        source0.setUrl("http://2.ch0");
        User user0 = new User();
        user0.setEmail("test0@test.com");
        user0.setLogin("test0");
        user0.setPassword("testpass0");
        User userFromDB0 = userService.add(user0);
        source0.setUser(userFromDB0);
        Source sourceFromDB0 = sourceService.add(source0);
        sourceService.delete(sourceFromDB0.getId());

        Source source1 = new Source();
        source1.setName("dvach1");
        source1.setUrl("http://2.ch1");
        User user1 = new User();
        user1.setEmail("test1@test.com");
        user1.setLogin("test1");
        user1.setPassword("testpass1");
        User userFromDB1 = userService.add(user1);
        source1.setUser(userFromDB1);
        Source sourceFromDB1 = sourceService.add(source1);

        Source source2 = new Source();
        source2.setName("dvach2");
        source2.setUrl("http://2.ch2");
        User user2 = new User();
        user2.setEmail("test2@test.com");
        user2.setLogin("test2");
        user2.setPassword("testpass2");
        User userFromDB2 = userService.add(user2);
        source2.setUser(userFromDB2);
        Source sourceFromDB2 = sourceService.add(source2);

        List<Source> sources = sourceService.getAll();
        assertThat(sources).contains(sourceFromDB1, sourceFromDB2);
        assertThat(sources.contains(sourceFromDB0)).isEqualTo(false);
        sourceService.delete(sourceFromDB1.getId());
        sourceService.delete(sourceFromDB2.getId());

        userService.delete(userFromDB0.getId());
        userService.delete(userFromDB1.getId());
        userService.delete(userFromDB2.getId());
    }



    @Test
    public void testSelect() throws Exception {

        Source source = new Source();
        source.setName("ain");
        source.setUrl("http://ain.ua");

        User user = new User();
        user.setEmail("minimus@gmail.com");
        user.setLogin("minis");
        user.setPassword("kjho");
        User userFromDB = userService.add(user);
        source.setUser(userFromDB);
        Source sourceFromDB = sourceService.add(source);
        int id = sourceFromDB.getId();
        Source sourceFromDBSel = sourceService.get(id);
        assertThat(sourceFromDB).isEqualTo(sourceFromDBSel);
        sourceService.delete(id);
        sourceFromDBSel = sourceService.get(id);
        assertThat(sourceFromDBSel).isNull();
        userService.delete(userFromDB.getId());
    }

}

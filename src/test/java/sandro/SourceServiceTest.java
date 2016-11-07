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

}

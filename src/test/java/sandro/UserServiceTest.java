package sandro;

import org.junit.Test;
import sandro.service.UserService;
import sandro.entity.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class UserServiceTest {

    UserService service = new UserService();

    @Test
    public void testSaveRecord() throws Exception {
        User user = new User();
        user.setEmail("chupak@gmail.comj");
        user.setLogin("chupak");
        user.setPassword("kapa");
        User userFromDB = service.add(user);
        assertThat(userFromDB).isNotNull();
        service.delete(userFromDB.getId());
    }

    @Test
    public void testDeleteRecord() throws Exception {
        User user = new User();
        user.setEmail("izya@mail.com");
        user.setLogin("izya");
        user.setPassword("izya");
        User userFromDB = service.add(user);
        int id = userFromDB.getId();
        service.delete(id);
        assertThat(service.get(id)).isNull();
    }

    @Test
    public void testSelect() throws Exception {
        User user1 = new User();
        user1.setEmail("minimus@gmail.com");
        user1.setLogin("minis");
        user1.setPassword("kjho");
        User userFromDB = service.add(user1);
        int id = userFromDB.getId();
        User userFromDBSel = service.get(id);
        assertThat(userFromDB).isEqualTo(userFromDBSel);
        service.delete(userFromDB.getId());
        userFromDBSel = service.get(id);
        assertThat(userFromDBSel).isNull();
    }


    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setEmail("informatio@gmail.com");
        user.setLogin("bronf");
        user.setPassword("man");
        user = service.add(user);
        user.setEmail("in.informatio@gmail.com");
        service.update(user);
        User userFromDB = service.get(user.getId());
        assertThat(userFromDB).isEqualTo(user);
        service.delete(user.getId());
    }

    @Test
    public void testGetAll() {
        User user0 = new User();
        user0.setEmail("deadshot@ukr.net");
        user0.setLogin("dead");
        user0.setPassword("shot");
        User userFromDB0 = service.add(user0);
        service.delete(userFromDB0.getId());

        User user1 = new User();
        user1.setEmail("pundik@ukr.net");
        user1.setLogin("pundik");
        user1.setPassword("squad");

        User user2 = new User();
        user2.setEmail("harley@ukr.net");
        user2.setLogin("harley");
        user2.setPassword("pudding");

        User userFromDB1 = service.add(user1);
        User userFromDB2 = service.add(user2);

        List<User> users = service.getAll();
        assertThat(users).contains(userFromDB1, userFromDB2);
        assertThat(users.contains(userFromDB0)).isEqualTo(false);
        service.delete(userFromDB1.getId());
        service.delete(userFromDB2.getId());
    }

}

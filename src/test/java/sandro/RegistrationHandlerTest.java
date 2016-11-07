package sandro;

import org.junit.Test;
import sandro.logic.RegistrationHandler;
import sandro.service.UserService;
import sandro.entity.User;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

public class RegistrationHandlerTest {

    @Test
    public void testCheckInputData() throws Exception {
        UserService userService = mock(UserService.class);
        RegistrationHandler handler = new RegistrationHandler("alphabet@gmail.com", "alphabet", "gamma");
        handler.setService(userService);
        List<User> usersFromDB = new LinkedList<User>();
        User usera= new User("a@ukr.net", "a", "aaaa");
        User userb= new User("b@ukr.net", "b", "bbbb");
        usersFromDB.add(usera);
        usersFromDB.add(userb);
        when(userService.getByEmail(handler.getEmail())).thenReturn(usersFromDB);
        assertThat(handler.checkInputData()).isNull();
        usersFromDB.clear();
        usersFromDB.add(usera);
        usersFromDB.add(userb);
        when(userService.getByLogin(handler.getLogin())).thenReturn(usersFromDB);
        assertThat(handler.checkInputData()).isNull();
        usersFromDB.clear();
        when(userService.getByLogin(handler.getLogin())).thenReturn(usersFromDB);
        assertThat(handler.checkInputData()).isNotNull();

    }
}

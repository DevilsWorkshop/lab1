package sandro.logic;

import sandro.entity.User;
import sandro.service.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex__000 on 11/7/2016.
 */
public class RegistrationHandler {

    private String email;
    private String password;
    private String login;
    private UserService service = new UserService();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationHandler(String email,  String login, String password) {
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public User checkInputData(){
        List<User> usersFromDB = new LinkedList <User> ();
        usersFromDB = service.getByEmail(email);
        if(usersFromDB.size()>0){
            return null;
        }
        usersFromDB.clear();
        usersFromDB = service.getByLogin(login);
        if(usersFromDB.size()>0){
            return null;
        }
        return new User(email, login, password);
    }

    public void setService(UserService service) {
        this.service = service;
    }
}

package sandro.service;

import sandro.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserService {

    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public User add(User user){
        em.getTransaction().begin();
        User userFromDB = em.merge(user);
        em.getTransaction().commit();
        return userFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public User get(int id){
        return em.find(User.class, id);
    }

    public void update(User user){
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public List<User> getAll(){
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }

    public List<User> getByLogin(String login){
        Query query = em.createQuery("SELECT s FROM User s where s.login=:login", User.class);
        query.setParameter("login", login);
        return (List<User>) query.getResultList();

    }

    public List<User> getByEmail(String email){
        Query query = em.createQuery("SELECT s FROM User s where s.email=:email", User.class);
        query.setParameter("email", email);
        return (List<User>) query.getResultList();

    }
}
package sandro.service;

import sandro.entity.Source;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class SourceService {


    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI2").createEntityManager();

    public Source add(Source source){
        em.getTransaction().begin();
        Source sourceFromDB = em.merge(source);
        em.getTransaction().commit();
        return sourceFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Source get(int id){
        return em.find(Source.class, id);
    }

    public void update(Source source){
        em.getTransaction().begin();
        em.merge(source);
        em.getTransaction().commit();
    }

    public List<Source> getAll(){
        TypedQuery<Source> namedQuery = em.createNamedQuery("Source.getAll", Source.class);
        return namedQuery.getResultList();
    }

    public List<Source> getByName(String name){
        Query query = em.createQuery("SELECT s FROM Source s where s.name=:name", Source.class);
        query.setParameter("name", name);
        return (List<Source>) query.getResultList();

    }

    public List<Source> getByURL(String url){
        Query query = em.createQuery("SELECT s FROM Source s where s.url=:url1", Source.class);
        query.setParameter("url1", url);
        return (List<Source>) query.getResultList();

    }


}

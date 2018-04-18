package com.springjson.services.daos;

import com.springjson.models.Person;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.springjson.services.connections.ConnectionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {

    @Autowired
    private ConnectionSession connectionSession;

    public int InsertPerson(Person person) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                session.save(person);
                session.beginTransaction().commit();
                session.close();
                return 1;
            }
        } catch (HibernateException e) {
            return -1;
        }
        return -1;
    }

    public int EditPerson(Person person) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                session.update(person);
                session.beginTransaction().commit();
                session.close();
                return 1;
            }
        } catch (HibernateException e) {
            return -1;
        }
        return -1;
    }

    public int DeletePerson(Person person) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                session.delete(person);
                session.beginTransaction().commit();
                session.close();
                return 1;
            }
        } catch (HibernateException e) {
            return -1;
        }
        return -1;
    }

    public Person GetPersonByID(Integer id) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                String sql = "from Person where id = :id";
                Query query = session.createQuery(sql);
                query.setParameter("id", id);
                Person person = (Person) query.uniqueResult();
                session.close();
                return person;
            }
            return null;
        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Person> GetPersonsByName(String name) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                List<Person> persons = session.createQuery("from Person where name LIKE :name ORDER BY id DESC").setParameter("name", name + '%').list();
                session.close();
                return persons;
            }
            return null;
        } catch (HibernateException e) {
            return null;
        }
    }

    public boolean CheckEmailPerson(String email) {
        try {
            Session session = connectionSession.OpenSession();
            if (session != null) {
                String sql = "from Person where email = :email";
                Query query = session.createQuery(sql);
                query.setParameter("email", email);
                Person person = (Person) query.uniqueResult();
                session.close();
                if (person == null) {
                    return true;
                }
            }
            return false;
        } catch (HibernateException e) {
            return false;
        }
    }
}

package ru.mahalov.personDAO.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mahalov.model.Person;
import ru.mahalov.personDAO.PersonService;

import java.util.List;

@Component
public class PersonServiceJPAImpl implements PersonService {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonServiceJPAImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void create(Person createdPerson) {
        Session session = sessionFactory.getCurrentSession();

        session.save(createdPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> read_All() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Person").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Person read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Override
    @Transactional
    public boolean update(int id, Person updatedPerson) {

        Session session = sessionFactory.getCurrentSession();
        session.update(updatedPerson);

        return true;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));

        return true;
    }
}

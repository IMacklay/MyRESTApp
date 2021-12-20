package ru.mahalov.personDAO.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mahalov.model.Person;
import ru.mahalov.personDAO.PersonService;

import java.util.List;

import static java.lang.Class.forName;

@Component
public class PersonServiceDBImpl implements PersonService {

    private final JdbcTemplate jdbcTemplate;

    public PersonServiceDBImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Person createdPerson) {

        int queryResult = jdbcTemplate.update(
                "insert into person (name, nickname, age) " +
                        "values (?, ?, ?)",
                createdPerson.getName(),
                createdPerson.getNickName(),
                createdPerson.getAge()
        );

    }

    @Override
    public List<Person> read_All() {
        return jdbcTemplate.query("select * from person order by id",new PersonRowMapper());
    }

    @Override
    public Person read(int id) {

        return jdbcTemplate.query("select * from person where id = ?",new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public boolean update(int id, Person updatedPerson) {

        int queryResult = jdbcTemplate.update(
                "update person " +
                    "set " +
                    "   name = ? ," +
                    "   nickname = ? ," +
                    "   age = ? " +
                    "where id = ?",

                    updatedPerson.getName(),
                    updatedPerson.getNickName(),
                    updatedPerson.getAge(),
                    id
        );

        return queryResult == 0;
    }

    @Override
    public boolean delete(int id) {

        int queryResult = jdbcTemplate.update("delete from person where id = ?", id);

        return queryResult == 0;
    }
}

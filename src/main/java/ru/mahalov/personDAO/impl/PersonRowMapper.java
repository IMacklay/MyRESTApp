package ru.mahalov.personDAO.impl;

import org.springframework.jdbc.core.RowMapper;
import ru.mahalov.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {


    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("Id"));
        person.setName(resultSet.getString("Name"));
        person.setNickName(resultSet.getString("NickName"));
        person.setAge(resultSet.getInt("Age"));

        return person;
    }
}

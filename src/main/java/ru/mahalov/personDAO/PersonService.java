package ru.mahalov.personDAO;
import ru.mahalov.model.Person;
import java.util.List;


public interface PersonService {

    void create(Person person);

    List<Person> read_All();

    Person read(int id);

    boolean update(int id, Person person);

    boolean delete(int id);

}

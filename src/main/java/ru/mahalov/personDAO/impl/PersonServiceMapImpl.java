package ru.mahalov.personDAO.impl;

import org.springframework.stereotype.Component;
import ru.mahalov.model.Person;
import ru.mahalov.personDAO.PersonService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PersonServiceMapImpl implements PersonService {

    // Хранилище данных
    private final Map<Integer, Person> PERSON_REPOSITORY_MAP = new HashMap<>();

    //Переменная генерации ID
    private final AtomicInteger PERSON_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Person person) {
        int id = PERSON_ID_HOLDER.incrementAndGet();
        person.setId(id);
        PERSON_REPOSITORY_MAP.put(id, person);
    }

    @Override
    public List<Person> read_All() {
        return new ArrayList<>(PERSON_REPOSITORY_MAP.values());
    }

    @Override
    public Person read(int id) {
        return PERSON_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(int id, Person person) {

        if (PERSON_REPOSITORY_MAP.containsKey(id)){
            person.setId(id);
            PERSON_REPOSITORY_MAP.put(id, person);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return PERSON_REPOSITORY_MAP.remove(id) != null;
    }
}

package ru.mahalov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mahalov.personDAO.PersonService;
import ru.mahalov.model.Person;

import java.util.List;

@RestController
@RequestMapping("/person_rest")
public class PersonRestController {

    private final PersonService personService;

    @Autowired
    PersonRestController(@Qualifier("personServiceJPAImpl") PersonService personService){
        this.personService = personService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Person person){

        personService.create(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> read(){
        final List<Person> listOfPerson = personService.read_All();

        return !listOfPerson.isEmpty() && listOfPerson != null
                ? new ResponseEntity<>(listOfPerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable int id){
        final Person person = personService.read(id);

        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, Person person){
        final boolean updated = personService.update(id, person);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){

        return personService.delete(id)
                ?new ResponseEntity<>(HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
}

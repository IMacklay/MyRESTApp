package ru.mahalov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mahalov.personDAO.PersonService;
import ru.mahalov.model.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonHttpController {

    private final PersonService personService;

    @Autowired
    PersonHttpController(@Qualifier("personServiceDBImpl") PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/new")
    public String newPerson(Model model){

        model.addAttribute("person",new Person());

        return "person/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "person/new";
        }
        personService.create(person);

        return "redirect:/person";
    }

    @GetMapping()
    public String read(Model model){

        final List<Person> listOfPerson = personService.read_All();
        model.addAttribute("person", listOfPerson);

        return  "person/index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable int id, Model model){
        final Person person = personService.read(id);

        model.addAttribute("person", person);

        return "person/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        final Person person = personService.read(id);

        model.addAttribute("person", person);

        return "person/edit";
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable int id){

        if (bindingResult.hasErrors())
            return "person/edit";

        final boolean updated = personService.update(id, person);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){

        personService.delete(id);

        return "redirect:/person";
    }
}

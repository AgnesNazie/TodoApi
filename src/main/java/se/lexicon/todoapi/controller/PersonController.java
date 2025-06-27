package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.dto.PersonDto;
import se.lexicon.todoapi.service.PersonService;

import java.util.List;
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    /* @GetMapping
    public List<Person> findAll() {
        System.out.println("Find all method has been executed");
        List<Person> people = new ArrayList<>();
        people.add(new Person(1L, "Nazie", "nazie@gmail.com", LocalDate.now()));
        people.add(new Person(2L, "Agnes", "agnes@gmail.com", LocalDate.now()));
        System.out.println("people: " + people);
        return people;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(Person person) {
        return new Person(3L, "Che", "che@gmail.com", LocalDate.now());
    }

    */
    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> findAll() {
        return personService.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable("id") Long personId) {
        System.out.println("personId: " + personId);
        return personService.findById(personId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        System.out.println("personDto: " + personDto);
        return personService.create(personDto);
    }
    //Delete person by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable ("id") Long personId) {
        personService.delete(personId);
    }
}

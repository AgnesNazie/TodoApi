package se.lexicon.todoapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.notify.model.Email;
import se.lexicon.notify.service.MessageService;
import se.lexicon.todoapi.dto.PersonDto;
import se.lexicon.todoapi.entity.Person;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    private PersonRepository personRepository;
    MessageService<Email> emailService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, MessageService<Email> emailService) {
        this.personRepository = personRepository;
        this.emailService = emailService;
    }

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(person -> new PersonDto(person.getId(), person.getName(), person.getEmail()))
                .toList();

    }

    @Override
    public PersonDto findById(Long id) {
        Person personEntity = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID not found" + id));
        return new PersonDto(personEntity.getId(), personEntity.getName(), personEntity.getEmail());

    }


    @Override
    public PersonDto create(PersonDto personDto) {
        //convert persondto to person entity
        Person personEntity = new Person();
        personEntity.setName(personDto.name());
        personEntity.setEmail(personDto.email());
        //save person entity
        Person savedEntity = personRepository.save(personEntity);

        //send confirmation email to created person
        emailService.sendMessage(new Email(savedEntity.getEmail(), "Welcome to Todo App", "Hello"
                + savedEntity.getName()
                + "\n\nYour Account has been successfully created with id" + savedEntity.getId()
        ));

        //convert person entity to persondto
        return new PersonDto(savedEntity.getId(), savedEntity.getName(), savedEntity.getEmail());
    }

    @Override
    public void delete(Long id) {
        //find person by id
        Person foundPerson = personRepository.findById(id)
                //if person doesnt exist throw exception
                .orElseThrow(() -> new IllegalArgumentException("ID not found" + id));
        //delete person
        personRepository.delete(foundPerson);
    }
}





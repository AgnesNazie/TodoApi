package se.lexicon.todoapi.service;

import se.lexicon.todoapi.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> findAll();

    PersonDto findById(Long id);
    PersonDto create(PersonDto personDto);
    void delete(Long id);
}

package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.todoapi.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person , Long> {
    // Custom query method to find a person by email
    Optional<Person> findByEmail(String email);

    // Custom query method to check if a person exists by email
    boolean existsByEmail(String email);

}

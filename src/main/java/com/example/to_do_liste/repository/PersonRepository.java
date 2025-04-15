package com.example.to_do_liste.repository;

import com.example.to_do_liste.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

//    Person findByUsername(String username);
    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);
}

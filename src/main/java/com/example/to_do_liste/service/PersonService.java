package com.example.to_do_liste.service;



import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerPerson(Person person) {
        // 1. Passwort verschlüsseln
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        // 2. Person in der DB speichern
        personRepository.save(person);
    }
    public boolean authenticate(String email, String rawPassword) {
        Optional<Person> personOptional = personRepository.findByEmail(email);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            return passwordEncoder.matches(rawPassword, person.getPassword());
        }
        return false;
    }
    public void printAllPersons() {
        List<Person> all = personRepository.findAll();
        all.forEach(person -> System.out.println(person.getUsername()));
        all.forEach(person -> System.out.println(person.getEmail()));
        all.forEach(person -> System.out.println(person.getPassword()));
    }


}


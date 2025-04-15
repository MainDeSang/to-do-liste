package com.example.to_do_liste.service;



import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public boolean authenticate(String username, String rawPassword) {
        Optional<Person> personOptional = personRepository.findByUsername(username);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            return passwordEncoder.matches(rawPassword, person.getPassword());
        }
        return false;
    }

}


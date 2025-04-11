package com.example.to_do_liste;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

// Zur Info:
// Diese Klasse habe ich vorübergehend erstellt, sie testet ob Daten in der Datenbank gespeichert werden
// Kann später wieder gelöscht werden. Wenn später das Backend steht (ohne Frontend) kann man mit 'Postman' Daten hinzufügen und bearbeiten.

@Component
public class DataSeeder implements CommandLineRunner {

    private final PersonRepository personRepository;

    public DataSeeder(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.findByUsername("testuser") == null) {
            Person p = new Person();
            p.setUsername("testuser");
            p.setPassword("testpasswort123");

            personRepository.save(p);
            System.out.println("***** Test-Person wurde gespeichert. *****");
        } else {
            System.out.println("***** Test-Person existiert bereits. *****");
        }
    }
}

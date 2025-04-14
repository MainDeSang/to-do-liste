package com.example.to_do_liste.controller;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final PersonService personService;

    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Person person) {
        personService.registerPerson(person);
        return ResponseEntity.ok("Benutzer erfolgreich registriert!");
    }

    // später:
    // @PostMapping("/login")
    // public ResponseEntity<String> login(...) {...}
}


package com.example.to_do_liste.controller;

import com.example.to_do_liste.dto.LoginRequest;
import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.security.JwtUtil;
import com.example.to_do_liste.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final PersonService personService;
    private final JwtUtil jwtUtil;

    public AuthController(PersonService personService, JwtUtil jwtUtil) {
        this.personService = personService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Person person) {
        personService.registerPerson(person);
        return ResponseEntity.ok("Benutzer erfolgreich registriert!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean success = personService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (success) {
            String token = jwtUtil.generateToken(loginRequest.getEmail()); // der jasonWebToken wird erstellt
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ungültige Zugangsdaten.");
            //ResponseEntity gibt Auskunft über den http status. ResponseEntity in Spring ist eine Klasse,
            // die verwendet wird, um eine vollständige HTTP-Antwort, einschließlich Statuscode, Header und
            // Antworttext, zu erstellen und zurückzugeben. Es wird oft in RESTful APIs eingesetzt,
            // um detaillierte Kontrolle über die Antwort zu haben.
        }
    }

}


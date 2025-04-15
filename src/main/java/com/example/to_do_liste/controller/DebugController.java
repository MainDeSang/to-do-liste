package com.example.to_do_liste.controller;


import com.example.to_do_liste.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class DebugController {


    private final PersonService personService;

    public DebugController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/debug/persons")
    public ResponseEntity<Void> printPersons() {

        personService.printAllPersons();
        return ResponseEntity.ok().build();
    }




}

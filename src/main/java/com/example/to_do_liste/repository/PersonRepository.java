package com.example.to_do_liste.repository;

import com.example.to_do_liste.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}

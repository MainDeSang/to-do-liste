package com.example.to_do_liste.repository;

import com.example.to_do_liste.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

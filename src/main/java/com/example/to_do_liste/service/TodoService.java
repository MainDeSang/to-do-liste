package com.example.to_do_liste.service;

import com.example.to_do_liste.dto.TodoDTO;
import com.example.to_do_liste.mapper.TodoMapper;
import com.example.to_do_liste.repository.TodoRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(TodoMapper::todoDTO)
                .collect(Collectors.toList());
    }
}

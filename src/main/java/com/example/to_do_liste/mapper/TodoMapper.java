package com.example.to_do_liste.mapper;

import com.example.to_do_liste.Todo;
import com.example.to_do_liste.dto.TodoDTO;


public class TodoMapper {
    //Method to mappt Todo Object.
    public static TodoDTO todoDTO(Todo todo) {
        TodoDTO tododto = new TodoDTO();
        todo.setTitle(todo.getTitle());
        todo.setDescription(todo.getDescription());
        todo.setStartDate(todo.getStartDate());
        todo.setEndDate(todo.getEndDate());
        todo.setStatus(todo.getStatus());
        todo.setProject(todo.getProject());
        todo.setStudyPlans(todo.getStudyPlans());
        return tododto;
    }

    public static Todo toEntity(TodoDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        return todo;
    }
}

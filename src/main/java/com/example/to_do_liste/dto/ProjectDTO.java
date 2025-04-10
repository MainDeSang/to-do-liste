package com.example.to_do_liste.dto;

import com.example.to_do_liste.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProjectDTO {
    private String title;
    private String description;
    private Set<Todo> todos = new HashSet<>();

}

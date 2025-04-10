package com.example.to_do_liste.mapper;

import com.example.to_do_liste.dto.ProjectDTO;
import com.example.to_do_liste.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//Mappt zwei Objekte mithilfe von MAPSTRUCT Plugin
@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper( ProjectMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    ProjectDTO projectToProjectDTO(Project project);
}

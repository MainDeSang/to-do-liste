package com.example.to_do_liste.mapper;

import com.example.to_do_liste.dto.ProjectDTO;
import com.example.to_do_liste.entity.Project;
import org.mapstruct.Mapper;

//Mappt zwei Objekte mithilfe von MAPSTRUCT Plugin
//✅ Wichtig: componentModel = "spring" sorgt dafür, dass Spring das Interface als Bean erkennt → du kannst es in der Service-Schicht per Autowiring verwenden.
@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDTO(Project project);
    Project toProject(ProjectDTO projectDTO);
}


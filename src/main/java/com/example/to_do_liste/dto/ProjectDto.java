package com.example.to_do_liste.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDto {
    private String title;
    private String description;
    private Long ownerId;
    private List<Long> todoIds;
}


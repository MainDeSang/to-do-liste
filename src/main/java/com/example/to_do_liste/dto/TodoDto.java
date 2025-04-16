package com.example.to_do_liste.dto;

import java.time.LocalDateTime;

public class TodoDto {

    public Long             id;
    public String           title;
    public String           description;
    public LocalDateTime    startDate;
    public LocalDateTime    endDate;
    public String           status;
    public LocalDateTime    deletedAt;
    public Long             projectId;
    public Long             ownerId;

}

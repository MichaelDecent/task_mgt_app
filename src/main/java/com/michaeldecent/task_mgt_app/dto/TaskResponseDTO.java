package com.michaeldecent.task_mgt_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponseDTO {

    private Integer taskId;
    private String title;
    private String description;
    private LocalDate due_date;
    private Boolean completion_status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

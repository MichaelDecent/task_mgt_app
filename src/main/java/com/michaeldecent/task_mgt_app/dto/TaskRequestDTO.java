package com.michaeldecent.task_mgt_app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TaskRequestDTO {
    private String title;
    private String description;
    private LocalDate due_date;
}

package com.michaeldecent.task_mgt_app.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Integer id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean completionStatus = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer userId;
}

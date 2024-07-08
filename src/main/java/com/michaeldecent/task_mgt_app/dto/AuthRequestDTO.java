package com.michaeldecent.task_mgt_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDTO {
    private String password;
    private String email;
}

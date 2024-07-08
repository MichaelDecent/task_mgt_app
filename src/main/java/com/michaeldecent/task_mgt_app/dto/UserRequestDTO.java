package com.michaeldecent.task_mgt_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    private String first_name;
    private String last_name;
    private String password;
    private String email;
}

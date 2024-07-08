package com.michaeldecent.task_mgt_app.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Integer user_id;
    private String first_name;
    private String last_name;
    private String email;
}

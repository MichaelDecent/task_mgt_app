package com.michaeldecent.task_mgt_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String access_token;
}

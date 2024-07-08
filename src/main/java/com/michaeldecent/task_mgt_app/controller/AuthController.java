package com.michaeldecent.task_mgt_app.controller;

import com.michaeldecent.task_mgt_app.dto.AuthRequestDTO;
import com.michaeldecent.task_mgt_app.dto.AuthResponseDTO;
import com.michaeldecent.task_mgt_app.dto.UserDTO;
import com.michaeldecent.task_mgt_app.request.RegisterRequest;
import com.michaeldecent.task_mgt_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.authenticateUser(request));
    }
}

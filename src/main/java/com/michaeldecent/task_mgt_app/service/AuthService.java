package com.michaeldecent.task_mgt_app.service;

import com.michaeldecent.task_mgt_app.dto.AuthRequestDTO;
import com.michaeldecent.task_mgt_app.dto.AuthResponseDTO;
import com.michaeldecent.task_mgt_app.dto.UserRequestDTO;
import com.michaeldecent.task_mgt_app.dto.UserResponseDTO;
import com.michaeldecent.task_mgt_app.model.Role;
import com.michaeldecent.task_mgt_app.model.User;
import com.michaeldecent.task_mgt_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserResponseDTO createUser(UserRequestDTO request) {
        var user = User.builder()
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return userService.convertUserToDto(user);
    }


    public AuthResponseDTO authenticateUser(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .access_token(jwtToken)
                .build();
    }

}


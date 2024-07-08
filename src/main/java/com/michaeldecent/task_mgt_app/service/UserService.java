package com.michaeldecent.task_mgt_app.service;

import com.michaeldecent.task_mgt_app.dto.TaskRequestDTO;
import com.michaeldecent.task_mgt_app.dto.TaskResponseDTO;
import com.michaeldecent.task_mgt_app.dto.UserDTO;
import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.model.User;
import com.michaeldecent.task_mgt_app.repository.TaskRepository;
import com.michaeldecent.task_mgt_app.repository.UserRepository;
import com.michaeldecent.task_mgt_app.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> retrieveAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO createTask(Integer userId, TaskRequestDTO newTaskData) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Task task = Task.builder()
                    .user(existingUser)
                    .title(newTaskData.getTitle())
                    .description(newTaskData.getDescription())
                    .dueDate(newTaskData.getDue_date())
                    .completionStatus(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            taskRepository.save(task);
            return convertTaskToDto(task);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<TaskResponseDTO> retrieveTaskByUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            return taskRepository.findAllByUser(existingUser)
                    .stream()
                    .map(this::convertTaskToDto)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<TaskResponseDTO> getTasksByUserIdAndOptionalFilters(Integer userId, Boolean completionStatus, LocalDate dueDate) {
        return taskRepository.findTasksByUserIdAndOptionalFilters(userId, completionStatus, dueDate)
                .stream()
                .map(this::convertTaskToDto)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer userId, RegisterRequest userData) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(userData.getEmail());
            existingUser.setFirstName(userData.getFirstName());
            existingUser.setLastName(userData.getLastName());
            existingUser.setPassword(passwordEncoder.encode(userData.getPassword()));
            userRepository.save(existingUser);
            return convertUserToDto(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void deleteUser(Integer UserId) {
        Optional<User> optionalUser = userRepository.findById(UserId);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public UserDTO convertUserToDto(User user) {
        return UserDTO.builder()
                .user_id(user.getId())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public TaskResponseDTO convertTaskToDto(Task task) {
        return TaskResponseDTO.builder()
                .taskId(task.getId())
                .completion_status(task.getCompletionStatus())
                .due_date(task.getDueDate())
                .created_at(task.getCreatedAt())
                .updated_at(task.getUpdatedAt())
                .description(task.getDescription())
                .title(task.getTitle())
                .build();
    }

}

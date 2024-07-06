package com.michaeldecent.task_mgt_app.service;

import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.model.User;
import com.michaeldecent.task_mgt_app.repository.TaskRepository;
import com.michaeldecent.task_mgt_app.repository.UserRepository;
import com.michaeldecent.task_mgt_app.request.RegisterRequest;
import com.michaeldecent.task_mgt_app.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final PasswordEncoder passwordEncoder;


    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public Task createTask(Integer userId, TaskRequest newTaskData) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Task task = Task.builder()
                    .user(existingUser)
                    .title(newTaskData.getTitle())
                    .description(newTaskData.getDescription())
                    .dueDate(newTaskData.getDueDate())
                    .completionStatus(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public List<Task> retrieveTaskByUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            return taskRepository.findAllByUser(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User updateUser(Integer userId, RegisterRequest userData) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setEmail(userData.getEmail());
            existingUser.setFirstname(userData.getFirstName());
            existingUser.setLastname(userData.getLastName());
            existingUser.setPassword(passwordEncoder.encode(userData.getPassword()));
            return userRepository.save(existingUser);
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
}

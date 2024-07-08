package com.michaeldecent.task_mgt_app.controller;

import com.michaeldecent.task_mgt_app.dto.TaskRequestDTO;
import com.michaeldecent.task_mgt_app.dto.TaskResponseDTO;
import com.michaeldecent.task_mgt_app.dto.UserRequestDTO;
import com.michaeldecent.task_mgt_app.dto.UserResponseDTO;
import com.michaeldecent.task_mgt_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> retrieveAllUsers() {
        return ResponseEntity.ok((userService.retrieveAllUsers()));
    }

    @PostMapping("{userId}/tasks")
    public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Integer userId, @RequestBody TaskRequestDTO newTaskData) {
        return ResponseEntity.ok(userService.createTask(userId, newTaskData));
    }

    @GetMapping("{userId}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> retrieveTasksByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.retrieveTaskByUser(userId));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUserIdAndOptionalFilters(
            @PathVariable Integer userId,
            @RequestParam(required = false) Boolean completionStatus,
            @RequestParam(required = false) LocalDate dueDate) {
        return ResponseEntity.ok(userService.getTasksByUserIdAndOptionalFilters(userId, completionStatus, dueDate));
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer userId, @RequestBody UserRequestDTO userData) {
        return ResponseEntity.ok(userService.updateUser(userId, userData));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

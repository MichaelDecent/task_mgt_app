package com.michaeldecent.task_mgt_app.service;

import com.michaeldecent.task_mgt_app.dto.TaskRequestDTO;
import com.michaeldecent.task_mgt_app.dto.TaskResponseDTO;
import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public TaskResponseDTO updateTask(Integer taskId, TaskRequestDTO taskData) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(taskData.getDescription());
            existingTask.setTitle(taskData.getTitle());
            existingTask.setUpdatedAt(LocalDateTime.now());
            existingTask.setDueDate(taskData.getDue_date());
            taskRepository.save(existingTask);
            return userService.convertTaskToDto(existingTask);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public void deleteTask(Integer taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            taskRepository.delete(optionalTask.get());
        } else {
            throw new RuntimeException("Task not found");
        }
    }

}

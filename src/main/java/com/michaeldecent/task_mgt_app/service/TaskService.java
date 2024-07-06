package com.michaeldecent.task_mgt_app.service;

import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.repository.TaskRepository;
import com.michaeldecent.task_mgt_app.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> retrieveTasksByCompletionStatus(Boolean completionStatus, Integer userId) {
        return taskRepository.findAllByCompletionStatusAndUserId(completionStatus, userId);
    }

    public List<Task> retrieveTasksByDueDate(LocalDate dueDate, Integer userId) {
        return taskRepository.findAllByDueDateAndUserId(dueDate, userId);
    }

    public Task updateTask(Integer taskId, TaskRequest taskData) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setDescription(taskData.getDescription());
            existingTask.setTitle(taskData.getTitle());
            existingTask.setUpdatedAt(LocalDateTime.now());
            existingTask.setDueDate(taskData.getDueDate());
            return taskRepository.save(existingTask);
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

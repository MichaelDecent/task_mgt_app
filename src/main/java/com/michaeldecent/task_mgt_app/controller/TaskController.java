package com.michaeldecent.task_mgt_app.controller;

import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.request.TaskRequest;
import com.michaeldecent.task_mgt_app.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("{userId}/byCompletionStatus")
    public ResponseEntity<List<Task>> retrieveTasksByCompletionStatus(@RequestParam Boolean completionStatus, @PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.retrieveTasksByCompletionStatus(completionStatus, userId));
    }

    @GetMapping("{userId}/byDueDate")
    public ResponseEntity<List<Task>> retrieveTasksByDueDate(@RequestParam LocalDate dueDate, @PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.retrieveTasksByDueDate(dueDate, userId));
    }

    @PutMapping("{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody TaskRequest taskData) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskData));
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

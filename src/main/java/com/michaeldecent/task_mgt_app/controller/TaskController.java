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
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> retrieveAllTasks() {
        return ResponseEntity.ok(taskService.retrieveAllTasks());
    }

    @GetMapping("/byCompletionStatus")
    public ResponseEntity<List<Task>> retrieveTasksByCompletionStatus(@RequestParam Boolean completionStatus) {
        return ResponseEntity.ok(taskService.retrieveTasksByCompletionStatus(completionStatus));
    }

    @GetMapping("/byDueDate")
    public ResponseEntity<List<Task>> retrieveTasksByDueDate(@RequestParam LocalDate dueDate) {
        return ResponseEntity.ok(taskService.retrieveTasksByDueDate(dueDate));
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody TaskRequest taskData) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskData));
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

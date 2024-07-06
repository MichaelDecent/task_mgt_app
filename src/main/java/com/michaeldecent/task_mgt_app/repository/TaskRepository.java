package com.michaeldecent.task_mgt_app.repository;

import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByCompletionStatus(Boolean completionStatus);

    List<Task> findAllByDueDate(LocalDate dueDate);

    List<Task> findAllByUser(User existingUser);
}

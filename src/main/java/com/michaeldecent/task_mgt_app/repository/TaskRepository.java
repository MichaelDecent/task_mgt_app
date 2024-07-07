package com.michaeldecent.task_mgt_app.repository;

import com.michaeldecent.task_mgt_app.model.Task;
import com.michaeldecent.task_mgt_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId " +
            "AND (:completionStatus IS NULL OR t.completionStatus = :completionStatus) " +
            "AND (:dueDate IS NULL OR t.dueDate = :dueDate)")
    List<Task> findTasksByUserIdAndOptionalFilters(
            @Param("userId") Integer userId,
            @Param("completionStatus") Boolean completionStatus,
            @Param("dueDate") LocalDate dueDate);

    List<Task> findAllByUser(User existingUser);
}

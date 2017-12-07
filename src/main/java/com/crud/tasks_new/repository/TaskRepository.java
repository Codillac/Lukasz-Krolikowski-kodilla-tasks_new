package com.crud.tasks_new.repository;

import com.crud.tasks_new.domain.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    Optional<Task> findById(Long id);

    void deleteTaskById(Long id);
}
package com.crud.tasks_new.service;

import com.crud.tasks_new.domain.Task;
import com.crud.tasks_new.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return repository.findById(taskId).get();
    }

    public Optional<Task> getTask(final Long taskId) {
        return repository.findById(taskId);
    }

    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }
}
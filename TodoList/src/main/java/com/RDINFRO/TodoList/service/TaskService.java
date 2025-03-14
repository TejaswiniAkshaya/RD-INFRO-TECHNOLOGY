package com.RDINFRO.TodoList.service;

import com.RDINFRO.TodoList.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(String description) {
        tasks.add(new Task(nextId++, description));
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public Task getTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateTask(int id, String description) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDescription(description);
        }
    }

}

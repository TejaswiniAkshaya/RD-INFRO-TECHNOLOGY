package com.RDINFRO.TodoList.controller;

import com.RDINFRO.TodoList.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String showTodoList(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "todo";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam("description") String description, Model model) {
        if (description == null || description.trim().isEmpty()) {
            model.addAttribute("error", "Task description cannot be empty");
        } else {
            taskService.addTask(description.trim());
        }
        model.addAttribute("tasks", taskService.getAllTasks());
        return "todo";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id, Model model) {
        taskService.deleteTask(id);
        model.addAttribute("tasks", taskService.getAllTasks());
        return "todo";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable int id, @RequestParam("description") String description, Model model) {
        if (description == null || description.trim().isEmpty()) {
            model.addAttribute("error", "Task description cannot be empty");
        } else {
            taskService.updateTask(id, description.trim());
        }
        model.addAttribute("tasks", taskService.getAllTasks());
        return "redirect:/todo"; // Redirect to prevent form resubmission
    }

}

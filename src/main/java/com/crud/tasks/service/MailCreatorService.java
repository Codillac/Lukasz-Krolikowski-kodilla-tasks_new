package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("tasks_url", "http://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Good Bye " + adminConfig.getAdminName() + "!");
        context.setVariable("company", adminConfig.getCompanyName() + ", E-mail: " + adminConfig.getCompanyMail() + ", Phone no.: " + adminConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildNumberOfTasksEmail() {
        String ending = (taskRepository.count() != 1) ? "s" : "";

        List<String> tasks = taskRepository.findAll().stream()
                .map(task -> task.getId() + ". " + task.getTitle())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_admin_male", adminConfig.getAdminSex().equals("male"));
        context.setVariable("number_of_tasks", "You have " + taskRepository.count() + " task" + ending + "in a database.");
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/number-of-tasks-in-database-mail", context);
    }
}

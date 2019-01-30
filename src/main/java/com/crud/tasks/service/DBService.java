package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    AdminConfig adminConfig;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }
    public Task getTask(final Long id){
        return repository.findById(id).orElse(null);
    }
    public Task saveTask(final Task task){
        Mail mail = new Mail(adminConfig.getAdminMail(),
                "New task added",
                "New card " + task.getTitle() + " has been added to your CRUD app",
                null);
        simpleEmailService.send(mail);
        return repository.save(task);
    }
    public void deleteTask(final Long id ){
        repository.delete(id);
    }
}

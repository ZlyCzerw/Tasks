package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("taskUrl", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbyeMessage", "Polecamy zapoznać się z ofertą wafelków z piór pawia. \n Pozdrawiamy zespół");
        context.setVariable("companyDetails", adminConfig);
        context.setVariable("show_button",true);
        context.setVariable("is_friend",true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);

    }
    public String buildScheduleEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("Manage your tasks!");
        functionality.add("Connect with Trello account!");
        functionality.add("API for uselessness provided!");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("taskUrl", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Check your tasks");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbyeMessage", "Polecamy zapoznać się z ofertą piór pawia z wafelków . Pozdrawiamy - zespół");
        context.setVariable("companyDetails", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-welcome-card-mail", context);
    }
}

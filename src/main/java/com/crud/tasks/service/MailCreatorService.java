package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("taskUrl", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbyeMessage", "Polecamy zapoznać się z ofertą wafelków z piór pawia. \n Pozdrawiamy zespół");
        context.setVariable("companyDetails", adminConfig.getCompanyDetails());
        return templateEngine.process("mail/created-trello-card-mail", context);



    }
}

package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class StaticWebPageController {


    @RequestMapping("/")
    public String index(Map<String,Object> model){
        model.put("variable", "My Thymeleaf variable");
        model.put("one",1);
        model.put("two",2);
        model.put("three",3);
        model.put("times", "*");
        model.put("minus", "-");
        model.put("plus", "+");
        model.put("equals", "=");
        model.put("message", "no co jest ziomek?");
        return "index";
    }
}


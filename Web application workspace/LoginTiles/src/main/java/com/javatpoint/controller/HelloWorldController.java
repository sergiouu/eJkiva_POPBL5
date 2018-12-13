package com.javatpoint.controller;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller  
public class HelloWorldController {  
    @RequestMapping("/hello")  
    public String helloWorld(Model m) {  
        String message = "Hello World, Spring MVC @ Javatpoint";  
        m.addAttribute("message", message);  
        return "hello";   
    }  
}

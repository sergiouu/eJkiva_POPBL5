package com.javatpoint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import probeHibernate.Product;
import probeHibernate.User;

@Controller  
@SessionAttributes 
public class CustomerController {

	@RequestMapping("/customer" )  
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Product products[] = {new Product("Zapatillas nike", 12.5f, 1), new Product("CD Negu Gorriak", 12.5f, 2)};
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        return "customer";  
    }  

}

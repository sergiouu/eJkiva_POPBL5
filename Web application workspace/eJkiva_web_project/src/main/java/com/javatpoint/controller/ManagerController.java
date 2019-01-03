package com.javatpoint.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.javapoint.repositories.ManagerRepository;

import probeHibernate.Ordert;
import probeHibernate.Product;
import probeHibernate.User;

@Controller  
@SessionAttributes 
public class ManagerController {
	
	ManagerRepository repo = new ManagerRepository();
	
	@RequestMapping("/manager" ) 
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "manager";  
    }  

	@RequestMapping("/manager/orders" )  
	public String products(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Ordert orders[] = {new Ordert((short)1, new Date(0), (short)1), new Ordert((short)1, new Date(0), (short)1)};
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "managerOrders";  
    }  
	
	@RequestMapping("/manager/stock" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "managerStock";  
    }  
	
	@RequestMapping("/manager/sales" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        String chartUrl = null;
        request.setAttribute("chart", chartUrl, WebRequest.SCOPE_REQUEST);
        return "sales";  
    }  
}

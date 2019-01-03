package com.javatpoint.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.javapoint.repositories.OperatorRepository;

import probeHibernate.Ordert;
import probeHibernate.Product;
import probeHibernate.User;

@Controller  
@SessionAttributes 
public class OperatorController {
	
	OperatorRepository repo = new OperatorRepository();
	
	@RequestMapping("/operator" ) 
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "operator";  
    }  

	@RequestMapping("/operator/orders" )  
	public String products(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Ordert orders[] = {new Ordert((short)1, new Date(0), (short)1), new Ordert((short)1, new Date(0), (short)1)};
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "operatorOrders";  
    }  
	
	@RequestMapping("/operator/stock" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Product products[] = repo.getAllProducts();
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        return "operatorStock";  
    }  
	
	@RequestMapping("/operator/warehouse" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        String chartUrl = null;
        request.setAttribute("chart", chartUrl, WebRequest.SCOPE_REQUEST);
        return "warehouse";  
    }  
	
}

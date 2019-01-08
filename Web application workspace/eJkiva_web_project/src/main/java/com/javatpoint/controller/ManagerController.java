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

/**
 * ManagerController
 * @author Leire
 * 
 */

@Controller  
@SessionAttributes 
public class ManagerController {
	
	ManagerRepository repo = new ManagerRepository();
	
	/**
     * This method will access the manager's main site.
     */
	@RequestMapping("/manager" ) 
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "manager";  
    }  

	/**
     * This method will access the manager's 'orders' option, where the manager will be able to see all the 
     * orders' history of the workstation.
     */
	@RequestMapping("/manager/orders" )  
	public String products(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Ordert orders[] = {new Ordert((short)1, new Date(0), (short)1), new Ordert((short)1, new Date(0), (short)1)};
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "managerOrders";  
    }  
	
	/**
     * This method will access the manager's 'stock' option, where the availability of the products (the stock)
     * of the warehouse will be shown.
     */
	@RequestMapping("/manager/stock" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "managerStock";  
    }  
	
	/**
     * This method will access the manager's 'sales' option, showing the sales history information of the warehouse.
     */
	@RequestMapping("/manager/sales" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        String chartUrl = null;
        request.setAttribute("chart", chartUrl, WebRequest.SCOPE_REQUEST);
        return "sales";  
    }  
}

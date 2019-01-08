package com.javatpoint.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import probeHibernate.Ordert;
import probeHibernate.Product;
import probeHibernate.User;

/**
 * CustomerController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes 
public class CustomerController {

	/**
     * This method will access the customer's main site.
     */
	@RequestMapping("/customer" ) 
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        return "customer";  
    }  

	/**
     * This method will access the customer's 'products' option, where the customer will be able to see all the 
     * products available on the store.
     */
	@RequestMapping("/customer/products" )  
	public String products(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Product products[] = {new Product("Zapatillas nike", 12.5f, 1), new Product("CD Negu Gorriak", 12.5f, 2)};
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        return "products";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/customer/orders" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Ordert orders[] = {new Ordert((short)1, new Date(0), (short)1), new Ordert((short)1, (new Date(0)), (short)1)};
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "customerOrders";  
    }  
	
	/**
     * This method will show a chart showing...
     * 
     */
	@RequestMapping("/customer/chart" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        String chartUrl = null;
        request.setAttribute("chart", chartUrl, WebRequest.SCOPE_REQUEST);
        return "customerChart";  
    }  

}

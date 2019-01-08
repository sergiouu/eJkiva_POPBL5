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

/**
 * OperatorController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes 
public class OperatorController {
	
	OperatorRepository repo = new OperatorRepository();
	
	/**
     * This method will access the operator's main site.
     */
	@RequestMapping("/operator" ) 
	public String customer(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        return "operator";  
    }  

	/**
     * This method will access the operator's 'orders' option, showing the orders of the warehouse at the time checked.
     */
	@RequestMapping("/operator/orders" )  
	public String products(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Ordert orders[] = {new Ordert((short)1, new Date(0), (short)1), new Ordert((short)1, new Date(0), (short)1)};
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "operatorOrders";  
    }  
	
	/**
     * This method will access the operator's 'stock' optionshowing the current stock of the products on the warehouse.
     */
	@RequestMapping("/operator/stock" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        Product products[] = repo.getAllProducts();
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        return "operatorStock";  
    }  
	
	/**
     * This method will access the operator's 'warehouse' option, where the operator will be able to 
     * see the overall situation of the warehouse, such as the robots' status, or an order's location.
     */
	@RequestMapping("/operator/warehouse" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        String chartUrl = null;
        request.setAttribute("chart", chartUrl, WebRequest.SCOPE_REQUEST);
        return "warehouse";  
    }  
	
}

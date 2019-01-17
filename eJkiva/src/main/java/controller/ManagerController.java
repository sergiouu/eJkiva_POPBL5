package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import entity.Order;
import entity.Product;
import entity.User;
import repository.ManagerRepository;
import repository.UserRepository;

/**
 * ManagerController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes
public class ManagerController {
	
	UserRepository urepo = new UserRepository();
	ManagerRepository repo = new ManagerRepository();
	
	/**
     * This method will access the manager's main site. 
	 * @throws IOException 
     */
	@RequestMapping("/manager" ) 
	public String customer(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 
                      
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
       // Product[] products = repo.getAllProducts();
       // request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        
        return "manager";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
	 * @throws IOException 
     */
	@RequestMapping("/manager/orders" )  
	public String orders(Model m, WebRequest request, HttpServletRequest hrequest, HttpServletResponse response) throws IOException {  
        m.addAttribute("command", new User()); 
        
        HttpSession session = hrequest.getSession(true);
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        
        Order orders[] = urepo.getAllOrders(user);
        String orderId = hrequest.getParameter("order");
        if(orderId!=null) {
        	session.setAttribute("order", urepo.findOrderById(Integer.parseInt(orderId)));
        	response.sendRedirect("/eJkiva/manager/order.html");
        }
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "managerOrders";  
    } 
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/manager/order" )  
	public String order(Model m, WebRequest request, HttpServletRequest hrequest) {  
        m.addAttribute("command", new User()); 

        Order order = (Order) request.getAttribute("order", WebRequest.SCOPE_SESSION);
        List<Product> products = urepo.getProductsFromOrder(order.getOrderId());
        System.out.println(products+"hbujvbg,k vcaisvdbOLC");
        request.setAttribute("product", products, WebRequest.SCOPE_REQUEST);
        request.setAttribute("order", order, WebRequest.SCOPE_REQUEST);
        return "managerOrder";  
    } 
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/manager/history" )  
	public String history(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Order orders[] = urepo.getAllOrders(user);
        //HashMap<Integer, Product[]> map = getMappedOrders(orders);
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "warehouseHistory";  
    } 
}

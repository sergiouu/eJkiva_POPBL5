package controller;

import java.io.IOException;

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
import repository.OperatorRepository;
import repository.UserRepository;

/**
 * OperatorController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes
public class OperatorController {
	
	UserRepository urepo = new UserRepository();
	OperatorRepository repo = new OperatorRepository();
	
	/**
     * This method will access the operator's main site. 
	 * @throws IOException 
     */
	@RequestMapping("/operator" ) 
	public String customer(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 
                        
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
       // Product[] products = repo.getAllProducts();
       // request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
        
        return "operator";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
	 * @throws IOException 
     */
	@RequestMapping("/operator/orders" )  
	public String orders(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 
        
        HttpSession session = hrequest.getSession(true);
        String orderId = hrequest.getParameter("order");
        System.out.println(orderId+"!!!***!!!!!");
        
        if (orderId!= null) {
			session.setAttribute("order",orderId);
        	response.sendRedirect("/eJkiva/operator/order.html");
        }
        
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Order orders[] = urepo.getAllOrders(user);
        //HashMap<Integer, Product[]> map = getMappedOrders(orders);
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "operatorOrders";  
    } 
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
	 * @throws IOException 
     */
	@RequestMapping("/operator/order" )  
	public String order(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        HttpSession session = hrequest.getSession(true);
        String orderId = hrequest.getParameter("order");
        if (orderId!= null) {
			session.setAttribute("order",urepo.findOrderById(Integer.parseInt(orderId)));
        	response.sendRedirect("/eJkiva/operator/order.html");
        }
        
        return "operatorOrder";  
    } 
}

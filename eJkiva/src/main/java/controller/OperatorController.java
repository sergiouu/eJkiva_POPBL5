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
import entity.Workstation;
import repository.OperatorRepository;
import repository.UserRepository;

/**
 * OperatorController contains the URL actions of the Operator user type
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
		User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

		Order orders[] = repo.getAllOrders();
		String orderId = hrequest.getParameter("order");
		if(orderId!=null) {
			session.setAttribute("order", urepo.findOrderById(Integer.parseInt(orderId)));
			response.sendRedirect("/eJkiva/manager/order.html");
		}
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

		Order order = (Order) request.getAttribute("order", WebRequest.SCOPE_SESSION);
		List<Product> products = urepo.getProductsFromOrder(order.getOrderId());
		System.out.println(products+"hbujvbg,k vcaisvdbOLC");
		request.setAttribute("product", products, WebRequest.SCOPE_REQUEST);
		request.setAttribute("order", order, WebRequest.SCOPE_REQUEST);
		return "operatorOrder";  
	} 
	
	/**
	 * This method will access the customer's 'orders' option, where the customer will be able to see the orders
	 * made through their history.
	 * @throws IOException 
	 */
	@RequestMapping("/operator/warehouse" )  
	public String warehouse(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
		m.addAttribute("command", new User()); 
		List<Workstation> workstations = repo.getAllWorkstations();
		request.setAttribute("workstations", workstations, WebRequest.SCOPE_REQUEST);
		return "warehouseState";  
	} 

}

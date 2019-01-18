package controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * ManagerController contains the URL actions of the Manager user type
 * @author Leire
 * @class ManagerController
 */
@Controller  
@SessionAttributes
public class ManagerController {

	UserRepository urepo = new UserRepository();
	ManagerRepository repo = new ManagerRepository();

	/**
	 * This method will access the manager's main site with the display of the warehouse. 
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
	 * This method will access the manager's 'orders' option, where the customer will be able to see the orders
	 * made through their history.
	 * @throws IOException 
	 */
	@RequestMapping("/manager/orders" )  
	public String orders(Model m, WebRequest request, HttpServletRequest hrequest, HttpServletResponse response) throws IOException {  
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
		return "managerOrders";  
	} 
	/**
	 * This method will show a specific order
	 */
	@RequestMapping("/manager/order" )  
	public String order(Model m, WebRequest request, HttpServletRequest hrequest) {  
		m.addAttribute("command", new User());
		User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION); 

		Order order = (Order) request.getAttribute("order", WebRequest.SCOPE_SESSION);
		List<Product> products = urepo.getProductsFromOrder(order.getOrderId());
		System.out.println(products+"hbujvbg,k vcaisvdbOLC");
		request.setAttribute("product", products, WebRequest.SCOPE_REQUEST);
		request.setAttribute("order", order, WebRequest.SCOPE_REQUEST);
		return "managerOrder";  
	} 

	/**
	 * This method will show a chart displaying the monthly behavior of the products selled.
	 */
	@RequestMapping("/manager/history" )  
	public String history(Model m, WebRequest request) {
		m.addAttribute("command", new User()); 
		User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

		String monthP = request.getParameter("month");		
		int month, day;
		if(monthP == null) month=12;
		else  month = Integer.parseInt(monthP);

		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) day = 31;
		else if (month == 4 || month == 6 || month == 9 || month == 11) day = 30;
		else day = 28;

		String value = repo.getMonth(month);

		List<Integer> listDatos = new ArrayList<>();
		List<String> listFecha = new ArrayList<>();	
		for(int i=1;i<=day;i++) {
			listDatos.add(repo.getItemsOutValue(i,month));
			listFecha.add(repo.getItemsOutDate(i,month).toString());
		}

		request.setAttribute("mylistD", listDatos, WebRequest.SCOPE_REQUEST);
		request.setAttribute("mylistF", listFecha, WebRequest.SCOPE_REQUEST);
		request.setAttribute("monthName", value, WebRequest.SCOPE_REQUEST);
		return "warehouseHistory";  
	} 
	/**
	 * This method will show all the products on stock.
	 */
	@RequestMapping("/manager/stock" )  
	public String stock(Model m, WebRequest request, HttpServletRequest hrequest) {  
		m.addAttribute("command", new User());
		User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION); 

		Product[] products = urepo.getAllProducts();
		System.out.println(products);
		request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
		return "managerStock";  
	} 

}

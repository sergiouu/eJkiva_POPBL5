package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import entity.Order;
import entity.Orderproduct;
import entity.Product;
import entity.User;
import entity.Usertype;
import repository.CustomerRepository;
import repository.UserRepository;
import utils.HibernateUtils;

/**
 * CustomerController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes 
public class CustomerController {

	List<Product> cart= new ArrayList<>();
	UserRepository urepo = new UserRepository();
	CustomerRepository repo = new CustomerRepository();
	/**
     * This method will access the customer's main site. Here, the customer will be able to see all the 
     * products available on the store.
	 * @throws IOException 
     */
	@RequestMapping("/customer" ) 
	public String customer(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 
        
		HttpSession session = hrequest.getSession(true);
        String productId = hrequest.getParameter("product");
        String addProductId = hrequest.getParameter("addProduct");
        
        List<Product> cartIsEmpty = (List<Product>) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
        if(cartIsEmpty!=null) {
        	cart = cartIsEmpty;
        }
        
        
        if (productId!= null) {
			session.setAttribute("product",repo.findProductById(Integer.parseInt(productId)));
        	response.sendRedirect("/eJkiva/customer/product.html");
        }
        
        if(addProductId!= null) {
        	System.out.println("PRODUCT ADDED!!!"+addProductId);
        	cart.add(repo.findProductById(Integer.parseInt(addProductId)));
        	System.out.println(cart);
        	hrequest.removeAttribute("addproduct");
        	session.removeAttribute("addproduct");
            session.setAttribute("cart", cart);
        }
        
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Product[] products = repo.getAllProducts();
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
                
        return "customer";  
    }  

	
	
	/**
     * This method will access the customer's 'products' option, where the customer will be able to see all the 
     * products available on the store.
	 * @throws IOException 
     */
	@RequestMapping("/customer/product" )  
	public String showProduct(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User()); 
        
        HttpSession session = hrequest.getSession(true);
        String productId = hrequest.getParameter("product");
        String addProductId = hrequest.getParameter("addProduct");
                
        if(addProductId!= null) {
        	System.out.println("PRODUCT ADDED!!!"+addProductId);
        	cart.add(repo.findProductById(Integer.parseInt(addProductId)));
            request.setAttribute("cart", cart, WebRequest.SCOPE_REQUEST);
        	response.sendRedirect("/eJkiva/customer.html");
        }
        
        Product product = (Product) request.getAttribute("product", WebRequest.SCOPE_SESSION);
        request.setAttribute("product", product, WebRequest.SCOPE_REQUEST);
        return "product";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/customer/orders" )  
	public String orders(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Order orders[] = urepo.getAllOrders(user);
        //HashMap<Integer, Product[]> map = getMappedOrders(orders);
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "customerOrders";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/customer/order" )  
	public String order(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Order orders[] = urepo.getAllOrders(user);
        //HashMap<Integer, Product[]> map = getMappedOrders(orders);
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "customerOrder";  
    } 
	/**
     * This method will show a chart showing...
     * 
     */
	@RequestMapping("/customer/productHistory" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        List<String>listP = repo.getProductList(sessionUser.getUserId());
        System.out.println(listP);
        List<Integer>listC = repo.getQuantityList(sessionUser.getUserId(), listP);
		System.out.println(listC.get(1)+"!!!!!!!!!!!!!!!!!!!!");
        request.setAttribute("myListP", listP, WebRequest.SCOPE_REQUEST);
        request.setAttribute("myListC", listC, WebRequest.SCOPE_REQUEST);
        return "productHistory";  
    }

	
		
}

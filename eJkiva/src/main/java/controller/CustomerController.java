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
 * CustomerController contains the URL actions of the Customer user type
 * @author Leire
 * 
 */  
@Controller  
@SessionAttributes 
public class CustomerController {

	
	List<Product> cart= new ArrayList<>();
	List<Integer> nums= new ArrayList<>();
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
        	nums = (List<Integer>) request.getAttribute("number", WebRequest.SCOPE_SESSION);
        }
        
        
        if (productId!= null) {
			session.setAttribute("product",repo.findProductById(Integer.parseInt(productId)));
        	response.sendRedirect("/eJkiva/customer/product.html");
        }
        
        if(addProductId!= null) {
        	System.out.println("PRODUCT ADDED!!!"+addProductId);
        	addProductToCart(repo.findProductById(Integer.parseInt(addProductId)) , 1);
        	hrequest.removeAttribute("number");
        	hrequest.removeAttribute("cart");
        	hrequest.removeAttribute("addproduct");
        	session.removeAttribute("addproduct");
            session.setAttribute("cart", cart);
            session.setAttribute("number", nums);
            session.setAttribute("totalCart", getTotalCart());
        }
        
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Product[] products = urepo.getAllProducts();
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
        	int num = Integer.parseInt(hrequest.getParameter("number"));
        	addProductToCart(repo.findProductById(Integer.parseInt(addProductId)) , num);
        	hrequest.removeAttribute("number");
        	hrequest.removeAttribute("cart");
        	hrequest.removeAttribute("addproduct");
        	session.removeAttribute("addproduct");
            session.setAttribute("cart", cart);
            session.setAttribute("number", nums);
            session.setAttribute("totalCart", getTotalCart());
        	response.sendRedirect("/eJkiva/customer.html");
        }
        
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Product product = (Product) request.getAttribute("product", WebRequest.SCOPE_SESSION);
        request.setAttribute("product", product, WebRequest.SCOPE_REQUEST);
        return "product";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
	 * @throws IOException 
     */
	@RequestMapping("/customer/orders" )  
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
        return "customerOrders";  
    }  
	
	/**
     * This method will access the customer's 'orders' option, where the customer will be able to see the orders
     * made through their history.
     */
	@RequestMapping("/customer/order" )  
	public String order(Model m, WebRequest request) {  
		m.addAttribute("command", new User()); 
		User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
		Order order = (Order) request.getAttribute("order", WebRequest.SCOPE_SESSION);
		List<Product> products = urepo.getProductsFromOrder(order.getOrderId());
		System.out.println(products+"hbujvbg,k vcaisvdbOLC");
		request.setAttribute("product", products, WebRequest.SCOPE_REQUEST);
		request.setAttribute("order", order, WebRequest.SCOPE_REQUEST);
		return "customerOrder";  
	} 
	
	/**
     * This method will show the actual cart of the customer
	 * @throws IOException 
     * 
     */
	@RequestMapping("/customer/cart" )  
	public String cart(Model m, WebRequest request, HttpServletRequest hrequest, HttpServletResponse response) throws IOException {  
        m.addAttribute("command", new User()); 
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        HttpSession session = hrequest.getSession(true);
        
        String delete = hrequest.getParameter("delete");
        System.out.println(delete+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if(delete != null) {

        	int pos = cart.indexOf(delete);
        	System.out.println(cart);
        	System.out.println(pos);
        	nums.remove(pos);
        	cart.remove(pos);

        	session.setAttribute("cart", cart);
        	session.setAttribute("number", nums);
        	response.sendRedirect("/eJkiva/customer.html");

        }
       
        
        request.setAttribute("numbers", nums, WebRequest.SCOPE_REQUEST);
        request.setAttribute("products", cart, WebRequest.SCOPE_REQUEST);
        return "cart";  
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
        List<Integer>listC = repo.getQuantityList(sessionUser.getUserId(), listP);
        request.setAttribute("myListP", listP, WebRequest.SCOPE_REQUEST);
        request.setAttribute("myListC", listC, WebRequest.SCOPE_REQUEST);
        return "productHistory";  
    }

	private void addProductToCart(Product p, int n) {
		boolean isRepeated = false;
		int pos = -1;
		for(Product pr: cart) {
			if(pr.getProductId() == p.getProductId()) {
				isRepeated = true;
				pos = cart.indexOf(pr);
				break;
			}
		}
		
		if(isRepeated) {
			int i = nums.get(pos) + n;
			nums.set(pos, i);
			
		}else {
			cart.add(p);
			nums.add(n);
		}
	
		System.out.println(cart);
		System.out.println(nums);

	}

	private int getTotalCart() {
		int total = 0;
		for(int n : nums) {
			total+=n;
		}
		return total;
	}


		
}

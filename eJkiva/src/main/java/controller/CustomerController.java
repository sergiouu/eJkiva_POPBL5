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
import utils.HibernateUtils;

/**
 * CustomerController
 * @author Leire
 * 
 */
@Controller  
@SessionAttributes 
public class CustomerController {
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
        
        if (productId!= null) {
			session.setAttribute("product",findProductById(Integer.parseInt(productId)));
        	response.sendRedirect("/eJkiva/customer/product.html");
        }
        
        
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Product[] products = getAllProducts();
        request.setAttribute("products", products, WebRequest.SCOPE_REQUEST);
                
        return "customer";  
    }  

	private Product findProductById(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("SELECT * FROM product where productID = '"+id+"'").addEntity(Product.class);
		Product product=query.uniqueResult();
		return product;
	}

	private Product[] getAllProducts() {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("SELECT * FROM product").addEntity(Product.class);
		List<Product> products=query.getResultList();
        Product[] allProducts = new Product[products.size()];
        for(int i=0;i<products.size(); i++) {
        	allProducts[i] = products.get(i);
        }
		return allProducts;
	}
	
	/**
     * This method will access the customer's 'products' option, where the customer will be able to see all the 
     * products available on the store.
     */
	@RequestMapping("/customer/product" )  
	public String showProduct(Model m, WebRequest request, HttpServletRequest hrequest) {  
        m.addAttribute("command", new User()); 
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
        Order orders[] = getAllOrders(user);
        //HashMap<Integer, Product[]> map = getMappedOrders(orders);
        request.setAttribute("orders", orders, WebRequest.SCOPE_REQUEST);
        return "customerOrders";  
    }  
	
	private HashMap<Integer, Product[]> getMappedOrders(Order[] orders) {
		HashMap<Integer, Product[]> hmap = new HashMap<Integer, Product[]>();
		Session session= HibernateUtils.getSessionFactory().openSession();
		for(int i = 0; i<orders.length; i++) {
			Query<Product> query=session.createSQLQuery("SELECT * FROM `order` WHERE userID = 1").addEntity(Product.class);
			List<Product> products=query.getResultList();
		}
		return hmap;
	}

	private Order[] getAllOrders(User user) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` WHERE userID = 1").addEntity(Order.class);
		List<Order> orders=query.getResultList();
		Order[] allOrders = new Order[orders.size()];
        for(int i=0;i<orders.size(); i++) {
        	allOrders[i] = orders.get(i);
        	System.out.println(allOrders[i]);
        }
		return allOrders;
	}

	/**
     * This method will show a chart showing...
     * 
     */
	@RequestMapping("/customer/productHistory" )  
	public String chart(Model m, WebRequest request) {  
        m.addAttribute("command", new User()); 
        User sessionUser = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        List<String>listP = getProductList(sessionUser.getUserId());
        System.out.println(listP);
        List<Integer>listC = getQuantityList(sessionUser.getUserId(), listP);
        System.out.println(listC);
        request.setAttribute("myListP", listP, WebRequest.SCOPE_REQUEST);
        request.setAttribute("myListC", listC, WebRequest.SCOPE_REQUEST);
        return "productHistory";  
    }

	private List<Integer> getQuantityList(int id, List<String> listP) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		List<Integer> quantities = new ArrayList<>();
		for(int i=0;i<listP.size();i++) {
			Query<Integer> query=session.createSQLQuery("select sum(quantity) as cantidad\r\n" + 
					"from product p \r\n" + 
					"inner join orderproduct oP on p.productID=oP.productID \r\n" + 
					"inner join mydatabase.`order` oT on oP.orderId = oT.orderID \r\n" + 
					"where oT.userId='"+id+"' and \r\n" + 
					"p.product_name = '"+listP.get(i)+"'"
					);
			System.out.println(query.uniqueResult()+"!!!!!!!!!!!!!!!!!");
			Integer quantity= query.uniqueResult().intValue();
			quantities.add(quantity);
		}
				
		return quantities;
	}

	@SuppressWarnings("deprecation")
	private List<String> getProductList(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();		
		Query<String> query=session.createSQLQuery("select distinct (p.product_name) as prodName \r\n" + 
				"from product p \r\n" + 
				"inner join orderproduct oP on p.productID=oP.productID \r\n" + 
				"inner join mydatabase.`order` oT on oP.orderId = oT.orderID \r\n" + 
				"where oT.userId='"+id+"'");
		List<String> products=query.getResultList();	
		return products;
	}  
		
}

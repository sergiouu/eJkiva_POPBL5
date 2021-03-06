package repository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Order;
import entity.Orderproduct;
import entity.Product;
import entity.User;
import utils.HibernateUtils;

/**
 * Customer Repository. Contains functions and sql queries to get information from database used by the customer. 
 * @class CustomerRepository
 * @author Leire
 *
 */
public class CustomerRepository {
	
	/**
     * This method will find a product by its ID
     * @param id      
     * @return Product
     */
	public Product findProductById(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("SELECT * FROM product where productID = '"+id+"'").addEntity(Product.class);
		Product product=query.uniqueResult();
		return product;
	}	
	
		
	/**
     * Function used for data display
     * @param id     
     * @param listP
     * @return List<Integer>
     */
	public List<Integer> getQuantityList(int id, List<String> listP) {
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
			//Oracle NUMBER maps to BigDecimal in Hibernate by default. Try setting the type to BigDecimal.
			Number n = query.uniqueResult();
			BigDecimal b = (BigDecimal) n;
			Integer quantity= b.intValue();
			quantities.add(quantity);
		}	
		return quantities;
	}
	
	/**
     * Function used for data display
     * @param id
     * @return List<String>
     */
	@SuppressWarnings("deprecation")
	public List<String> getProductList(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();		
		Query<String> query=session.createSQLQuery("select distinct (p.product_name) as prodName \r\n" + 
				"from product p \r\n" + 
				"inner join orderproduct oP on p.productID=oP.productID \r\n" + 
				"inner join mydatabase.`order` oT on oP.orderId = oT.orderID \r\n" + 
				"where oT.userId='"+id+"'");
		List<String> products=query.getResultList();	
		return products;
	}


	/**
     * Function used to create a new order on the database.
     * @param cart
     * @param nums
     * @param user
     */
	public void createOrder(List<Product> cart, List<Integer> nums, User user) {
				
		Session session=HibernateUtils.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction(); 
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));		
		Order order = new Order(user, dateobj);		
		session.save(order);
		
		for(int i=0;i<cart.size();i++) {
			Orderproduct op = new Orderproduct(order, cart.get(i), nums.get(i).shortValue());
			session.save(op);
		}
        tx.commit();
		
	}  
	
	
}

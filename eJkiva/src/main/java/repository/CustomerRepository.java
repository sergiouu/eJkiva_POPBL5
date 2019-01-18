package repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Order;
import entity.Product;
import entity.User;
import utils.HibernateUtils;

/**
 * User Repository. Contains sql queries to get information from database. 
 * 
 * @author Leire
 *
 */
public class CustomerRepository {
	
	/**
     * This method will find a product by its ID
     */
	public Product findProductById(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("SELECT * FROM product where productID = '"+id+"'").addEntity(Product.class);
		Product product=query.uniqueResult();
		return product;
	}
	
	
		
	/**
     * Function used for data display
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
	
	
	
	/*
	private HashMap<Integer, Product[]> getMappedOrders(Order[] orders) {
		HashMap<Integer, Product[]> hmap = new HashMap<Integer, Product[]>();
		Session session= HibernateUtils.getSessionFactory().openSession();
		for(int i = 0; i<orders.length; i++) {
			Query<Product> query=session.createSQLQuery("SELECT * FROM `order` WHERE userID = 1").addEntity(Product.class);
			List<Product> products=query.getResultList();
		}
		return hmap;
	}

	 */
}

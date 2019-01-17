package repository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Order;
import entity.Product;
import entity.User;
import entity.Usertype;
import utils.HibernateUtils;
/**
 * User Repository. Contains sql queries to get information from database. 
 * 
 * @author Leire
 *
 */
public class UserRepository {

	/**
     * This method will check a user and its password on the database
     */
	public User checkUser(String uname, String password) {
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<User> query=session.createSQLQuery("SELECT * FROM user where username = '"+uname+ "' and password ='"+password+"'").addEntity(User.class);
		//List<User> users=query.list();		
		User user=query.uniqueResult();
		return user;
	}
	/**
     * This method will return the usertype of the user given
     */
	public Usertype checkUsertype(User user) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where usertypeID = '"+user.getUsertype().getUsertypeId()+"'").addEntity(Usertype.class);
		Usertype usertype=query.uniqueResult();
		return usertype;
	}
	/**
     * This method will find a user and return its type
     */
	public Usertype getUsertypeById(int id) {
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where usertypeID = '"+id+"'").addEntity(Usertype.class);
		//List<User> users=query.list();		
		Usertype usertype=query.uniqueResult();
		return usertype;
	}
	
	/**
     * This method will return all the orders of the user given
     */
	public Order[] getAllOrders(User user) {
		System.out.println(user.getUsertype().getUsertypeId()+"!!!!!!!!!!!!!!!!!");
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` WHERE userID = '"+user.getUserId()+"'").addEntity(Order.class);
		List<Order> orders=query.getResultList();
		Order[] allOrders = new Order[orders.size()];
        for(int i=0;i<orders.size(); i++) {
        	allOrders[i] = orders.get(i);
        }
		return allOrders;
	}
	
	/**
     * This method will find an order by its ID
     */
	public Order findOrderById(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` where orderID = '"+id+"'").addEntity(Order.class);
		Order order=query.uniqueResult();
		return order;
	}
	
	/**
     * This method will find an order by its ID
     */
	public List<Product> getProductsFromOrder(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("select p.productID as productID, p.product_name as product_name, o.dateDelivered as dateDelivered, p.image as image, op.quantity as quantity\r\n" + 
				"from product p\r\n" + 
				"inner join orderproduct op on p.productID =  op.productID\r\n" + 
				"inner join `order` o on op.orderID = o.orderID\r\n" + 
				"where o.orderID = '"+id+"';").addEntity(Product.class);
		List<Product> products=query.getResultList();
		return products;
	}
	
	
	
}

package repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @class UserRepository
 * @author Leire
 *
 */

public class UserRepository {

	/**
     * Adds a user to the database
     * @param usertype 
     * @param uname
     * @param password
     * @param rname
     * @param surname
     * @param mail
     * @param bornDat     
     * @return User
     */
	public User addUser(Usertype usertype, String uname, String password, String rname, String surname, String mail,
			String bornDat) throws Exception {

		Session session=HibernateUtils.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction(); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(bornDat);
		
		User newuser = new User(usertype, uname, password, rname, surname, mail, date);
		
		session.save(newuser);
        tx.commit();
		return newuser;
	}
	
	/**
     * This method will check a user and its password on the database
     * @param uname
     * @param password     
     * @return User
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
     * @param user     
     * @return Usertype
     */
	public Usertype checkUsertype(User user) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where usertypeID = '"+user.getUsertype().getUsertypeId()+"'").addEntity(Usertype.class);
		Usertype usertype=query.uniqueResult();
		return usertype;
	}
	/**
     * This method will find a user and return its type
     * @param id     
     * @return Usertype
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
     * @param user     
     * @return Order[]
     */
	public Order[] getAllOrders(User user) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` WHERE userID = '"+user.getUserId()+"' ORDER BY dateOrder desc").addEntity(Order.class);
		List<Order> orders=query.getResultList();
		Order[] allOrders = new Order[orders.size()];
        for(int i=0;i<orders.size(); i++) {
        	allOrders[i] = orders.get(i);
        }
		return allOrders;
	}
	
	/**
     * This method will find an order by its ID
     * @param id     
     * @return Order
     */
	public Order findOrderById(int id) {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` where orderID = '"+id+"'").addEntity(Order.class);
		Order order=query.uniqueResult();
		return order;
	}
	
	/**
     * This method will find the products of an order
     * @param id     
     * @return List<Product>
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
		

	/**
     * This method will return all the products available on stock
     * @return Product[]
     */
	public Product[] getAllProducts() {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Product> query=session.createSQLQuery("SELECT * FROM product").addEntity(Product.class);
		List<Product> products=query.getResultList();
        Product[] allProducts = new Product[products.size()];
        for(int i=0;i<products.size(); i++) {
        	allProducts[i] = products.get(i);
        }
		return allProducts;
	}
}

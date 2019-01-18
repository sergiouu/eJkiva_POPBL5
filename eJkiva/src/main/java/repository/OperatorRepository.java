package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Order;
import entity.Product;
import entity.User;
import utils.HibernateUtils;

public class OperatorRepository {
	/**
     * This method will return all the orders of the user given
     */
	public Order[] getAllOrders() {
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Order> query=session.createSQLQuery("SELECT * FROM `order` ORDER BY dateOrder desc").addEntity(Order.class);
		List<Order> orders=query.getResultList();
		Order[] allOrders = new Order[orders.size()];
        for(int i=0;i<orders.size(); i++) {
        	allOrders[i] = orders.get(i);
        }
		return allOrders;
	}
}

package repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Order;
import entity.User;
import utils.HibernateUtils;

public class ManagerRepository {
	public String getMonth(int month) {
		//for definitu hemen
				
				HashMap<Integer, String>  map = new HashMap<Integer, String>();
		        map.put(1, "January");
		        map.put(2, "February");
		        map.put(3, "March");
		        map.put(4, "April");
		        map.put(5, "May");
		        map.put(6, "June");
		        map.put(7, "July");
		        map.put(8, "August");
		        map.put(9, "September");
		        map.put(10, "October");
		        map.put(11, "November");
		        map.put(12, "December");
		        return map.get(month);
	}
	
	public int getItemsOutValue(int day, int month) {
		Session session= HibernateUtils.getSessionFactory().openSession();
			Query<Integer> query=session.createSQLQuery("select sum(o.quantity) as total \r\n" + 
					"from product p\r\n" + 
					"inner join orderproduct o on p.productID=o.productID\r\n" + 
					"inner join mydatabase.`order` ot on o.orderId=ot.orderID\r\n" + 
					"where month(ot.dateOrder)='"+month+"'\r\n" + 
					"and year(ot.dateOrder)='2018' \r\n" + 
					"and day(ot.dateOrder)='"+day+"'"
					);
			Number n = query.uniqueResult();
			BigDecimal b = (BigDecimal) n;
			Integer value= b.intValue();
		return value;
	}
	
	public Date getItemsOutDate(int day, int month) {
		Session session= HibernateUtils.getSessionFactory().openSession();
			Query<Date> query=session.createSQLQuery("select distinct ot.dateOrder as fecha \r\n" + 
					"from product p\r\n" + 
					"inner join orderproduct o on p.productID=o.productID\r\n" + 
					"inner join mydatabase.`order` ot on o.orderId=ot.orderID\r\n" + 
					"where month(ot.dateOrder)='"+month+"'\r\n" + 
					"and year(ot.dateOrder)='2018' \r\n" + 
					"and day(ot.dateOrder)='"+day+"'"
					);
			Date date = query.uniqueResult();
		return date;
	}
	
	/**
     * This method will return all the orders of the warehouse's history
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

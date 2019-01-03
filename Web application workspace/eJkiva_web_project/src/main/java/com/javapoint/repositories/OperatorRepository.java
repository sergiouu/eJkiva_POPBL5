package com.javapoint.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;

import probeHibernate.Product;
import probeHibernate.User;
import utils.HibernateUtils;

public class OperatorRepository {

	public Product[] getAllProducts() {
		
		Product[] products = null;
		
		System.out.println("HEMEN");
		Session session = HibernateUtils.getSessionFactory().openSession();
		Query<Product> query = session.createQuery("from product");
		//List<User> users=query.list();
		System.out.println("HEMEN:"+query.list());
		products = (Product[]) query.getResultList().toArray();
		
		return products;
	}
}

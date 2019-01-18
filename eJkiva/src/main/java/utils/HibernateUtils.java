package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtils. Contains the configuration of Hibernate. 
 * @class HibernateUtils
 * @author Leire
 *
 */
public class HibernateUtils {
private static SessionFactory sessionFactory;
	
	static {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(entity.User.class);
		configuration.configure();
		configuration.addResource("/entity/User.hbm.xml");
		configuration.addResource("/entity/Usertype.hbm.xml");
		configuration.addResource("/entity/Product.hbm.xml");
		configuration.addResource("/entity/Departament.hbm.xml");
		configuration.addResource("/entity/Order.hbm.xml");
		configuration.addResource("/entity/Orderproduct.hbm.xml");
		configuration.addResource("/entity/Workstation.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		/*sessionFactory= new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();*/
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}

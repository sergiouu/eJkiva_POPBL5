package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	
	static {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(probeHibernate.User.class);
		configuration.configure();
		configuration.addResource("/probeHibernate/User.hbm.xml");
		configuration.addResource("/probeHibernate/Usertype.hbm.xml");
		configuration.addResource("/probeHibernate/Ordert.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(configuration.getProperties());
		sessionFactory= configuration.buildSessionFactory(builder.build());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

}

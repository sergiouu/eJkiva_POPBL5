package programa;



import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import probeHibernate.Ordert;
import probeHibernate.User;
import probeHibernate.Usertype;
import utils.HibernateUtils;

public class prueba {
  private static Session session;
  
	public static void main(String[] args) {
		
		
		consult();
				 
		//createUser(1224,"Sergio123","123","Sergio","Erreka","sergio@sergio","25/05/1998",1);
		
		System.out.println("Manex Hemen Nago");

	}

	private static void createUser(int userId, String uname, String password, String rname, String surname, String mail, String date, int userTypeId) {
		
		session=HibernateUtils.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction();
		Date dat;
		dat=transformDate(date);
		User user=new User((short)userId, uname,password, rname, surname, mail,dat,(byte)userTypeId);
		session.save(user);
		
		tx.commit();
		
	}
	
	
	public static Date transformDate(String startDateString) {
		 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		Date startDate = null;
		try {
		    startDate = (Date) df.parse(startDateString);
		    String newDateString = df.format(startDate);
		    System.out.println(newDateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return startDate;

	}
	
	public static void consult() {
	
		Session session=HibernateUtils.getSessionFactory().openSession();
		
		Query query2 = session.createQuery("from User ");
		//Query query = session.createQuery("from User left join User.userTypeId as userType ");
		
		ArrayList<User> u= (ArrayList) query2.list();
	//	Ordert o= (Ordert) query2.getSingleResult();
	//	ArrayList<Usertype> ut=(ArrayList) query.list();
		
		for(User a : u) {
		System.out.println(a.getuType().getDescription());}
	//	System.out.println(u.get(1).getuType().getDescription());
		
		
		
		
	 }
	
	
	public static void consultLogin() {
		session=HibernateUtils.getSessionFactory().openSession();
		
		BigInteger numb=(BigInteger) session.createSQLQuery("SELECT count(userID) FROM user ").uniqueResult();
		
		System.out.println("Hau da nire zenbakia"+numb);
		

	 }
	public static void consultLogin2() {
		session=HibernateUtils.getSessionFactory().openSession();

		List<Object[]> authors = session.createQuery("from User u join u.userTupeId uT ").getResultList();
		
		System.out.println(authors);
		System.out.println(authors.size());
		/*Query<UserType> query =  session.createQuery(
		        "SELECT * "
		        + "FROM  userType" );	*/
		
		
//		List<Object[]> users=query.list();
		
//		for(Object[] u: users) {
	//		System.out.println(u.length);
		//}
		

	 }
	
	

}

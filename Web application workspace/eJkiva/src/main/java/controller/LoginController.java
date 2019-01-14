package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import entity.Order;
import entity.User;
import entity.Usertype;
import utils.HibernateUtils;

/**
 * Login class.
 * 
 * @author Leire
 *
 */

@Controller  
@SessionAttributes 
public class LoginController {
		
	/**
     * This method will login a user and direct them depending on their type
     */
	@RequestMapping("/login")  
    public String login(Model m, HttpServletResponse response, HttpServletRequest request) throws IOException {  
        m.addAttribute("command", new User());

		//UserRepository repo = new UserRepository();
        String action = request.getParameter("action");
		HttpSession session = request.getSession(true);

		switch (action){
			case "Log In":

				String username = request.getParameter("username");
				String password = request.getParameter("password");
	
				User user = null;
				System.out.println("Username: "+username);
				System.out.println("Password: "+password);
				
				//Test loggin
				if(username!=null && password!=null){
					//user = repo.checkUser(username, password);
					user = checkUser(username, password);
					System.out.println("USER "+user);
				}
					
				//Save login in session
				if(user != null){
					System.out.println("Username:"+user.getUname());
					session.setAttribute("user",user);
					request.setAttribute("message", "login.successful");
					Usertype usertype = checkUsertype(user);
					System.out.println(usertype);
			        return "customer"; 
				}else{
					System.out.println("No user loaded");
					session.setAttribute("user", null);
					request.setAttribute("errorL", "Wrong username ("+username+") or password ("+password+").");//"login.uperror");
					request.setAttribute("username", username);
					request.setAttribute("password", password);
			        return "login"; 
				}
			case "Register Now":
				System.out.println("REGISTER");
				String uname = request.getParameter("username");
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				String email = request.getParameter("email");
				String borndate = request.getParameter("borndat");
				String pwd = request.getParameter("password");
				String passwordRepeat = request.getParameter("confirm-password");
				Usertype utype = getUsertypeById(1);
				if(pwd.equals(passwordRepeat)) {
					User newUser = addUser(utype, uname, pwd, name, surname, email, borndate);
					System.out.println(newUser);
			        return "customer"; 
				}else {
					System.out.println("Passwords don't match!");
					request.setAttribute("errorR", "Passwords don't match!");
					request.setAttribute("register", true);
			        return "login"; 
				}
		} 
        return "login"; 
    }  	
	
	public User checkUser(String uname, String password) {
	
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<User> query=session.createSQLQuery("SELECT * FROM user where uName = '"+uname+ "' and password ='"+password+"'").addEntity(User.class);
		//List<User> users=query.list();
		
		User user=query.uniqueResult();
		return user;
	}
	
	public Usertype checkUsertype(User user) {
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where id = '"+user.getUsertype()+"'").addEntity(Usertype.class);
		//List<User> users=query.list();
		
		Usertype usertype=query.uniqueResult();
		return usertype;
	}
	
	public User addUser(Usertype usertype, String uname, String password, String rname, String surname, String mail,
			String bornDat) {

		Session session=HibernateUtils.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction(); 
		User newuser = new User(usertype, uname, password, rname, surname, mail, bornDat);
		session.save(newuser);
        tx.commit();
		return newuser;
	}
	
	public Usertype getUsertypeById(int id) {
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where id = '"+id+"'").addEntity(Usertype.class);
		//List<User> users=query.list();
		
		Usertype usertype=query.uniqueResult();
		return usertype;
	}
	
	
	
}

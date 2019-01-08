package com.javatpoint.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.javatpoint.form.Contact;

import probeHibernate.User;
import probeHibernate.Usertype;
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
    public String login(Model m, WebRequest request, HttpServletResponse response, HttpServletRequest hrequest) throws IOException {  
        m.addAttribute("command", new User());
      //  HttpSession session = request.getSession(true);
        
        String u = request.getParameter("uname");
        String p = request.getParameter("password");
        
        System.out.println("REQUEST"+hrequest.getMethod());
         if("POST".equals(hrequest.getMethod())) {
        	 
        	 if(hrequest.getParameter("action")!= null) {
        		 if(hrequest.getParameter("action").equals("logout")) {
        	 
        		 System.out.println("*************************LOGOUUUUTTT******************************");
        		 }
        	 }
        	 
        	 User user = consult(u, p);
        	 if(user != null) {
            	System.out.println("ENTER");
            	System.out.println("LOGIN USER"+user);
            	request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
            //	request.setAttribute("message", "login.successful");
            	m.addAttribute("user", user);
            	response.sendRedirect("/eJkiva_web_project/"+pageType(user.getUserTypeId())+".html");
            	//return pageType(user.getUserTypeId());
            }else {
            	System.out.println("FAIL");
            	System.out.println("No user loaded");
            	request.setAttribute("error", "The user or password are incorrect.", WebRequest.SCOPE_REQUEST);
            	response.sendRedirect("/eJkiva_web_project/login.html");
            }
        	
        }
              
        return "login";  
    }  	
	
	/**
     * This function will check the user and password on the database
     * @param name the username of the logger
     * @param password the password of the logger
     */
	private static User consult(String name, String password) {
		String uType;
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<User> query=session.createSQLQuery("SELECT * FROM user where uName = '"+name+ "' and password ='"+password+"'").addEntity(User.class);
		//List<User> users=query.list();
		
		User user=query.uniqueResult();
		System.out.println(user);
		if(user!=null)return user;
		else return null;	

	 }

	/**
     * This method will register a new user on the database
     */
	@RequestMapping("/register")  
    public String register(Model m, WebRequest request) { 
		m.addAttribute("command", new User());
		Session session=HibernateUtils.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction();   
		        
	    if(request.getParameter("uname")!= null) {

	    	User newUser = new User(
					request.getParameter("uname"),
					request.getParameter("password"),
					request.getParameter("rname"), 
					request.getParameter("surname"),
					request.getParameter("mail"),
					request.getParameter("bornDat"),
					(byte)1);
	    	
	    	session.save(newUser);
	        tx.commit();
	                
	        return pageType(newUser.getUserTypeId());
		}
	    
        return "register";  
    }

	private static Date transformDate(String dateString) {
		 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = null;
		try {
		    date = (Date) df.parse(dateString);
		    date = df.parse(df.format(date));
		    System.out.println("Date: "+date);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return date;

	}
	
	/**
     * This function will return the type of user that is being logged
     * @param user the user that is being logged
     */
	private static String pageType(User user) {
		Usertype uType;
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<Usertype> query=session.createSQLQuery("SELECT * FROM usertype where userTypeID = '"+user.getUserTypeId()+ "'").addEntity(Usertype.class);
		//List<User> users=query.list();
		
		uType=query.uniqueResult();
		
		return uType.getUtype();	

	 }
	
	/**
     * This method returns the user type name depending on the key given
     * @param i the identification key of each user type
     */
	private String pageType(int i) {
		
		String type=null;
		
		if(i == 1) type = "customer";
		else if(i == 2) type = "operator";
		else if(i == 3) type = "manager";
		else type ="admin";
		return type;
	}

}

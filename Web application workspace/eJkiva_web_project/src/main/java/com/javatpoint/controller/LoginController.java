package com.javatpoint.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import utils.HibernateUtils;

@Controller  
@SessionAttributes 
public class LoginController {
	
	@RequestMapping("/login")  
    public String login(Model m, WebRequest request, HttpServletResponse response) throws IOException {  
        m.addAttribute("command", new User());
        String u = request.getParameter("uname");
        String p = request.getParameter("password");
        
        System.out.println(u + " "+p);
         if(u!=null && p!=null) {
        	 User user = consult(u, p);
        	 if(user != null) {
            	System.out.println("ENTER");
            	request.setAttribute("user", user, WebRequest.SCOPE_REQUEST);
            	response.sendRedirect("/eJkiva_web_project/"+pageType(user.getUserTypeId())+".html");
            	//return pageType(user.getUserTypeId());
            }else {
            	System.out.println("FAIL");
            	return null;
            }
        	
        }
            
        
        return "login";  
    }  
	
	private static User consult(String name, String password) {
		String uType;
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Query<User> query=session.createSQLQuery("SELECT * FROM user where uName = '"+name+ "' and password ='"+password+"'").addEntity(User.class);
		//List<User> users=query.list();
		
		User user=query.uniqueResult();
		
		return user;	

	 }

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

	private String pageType(int i) {
		
		String type=null;
		
		if(i == 1) type = "customer";
		else if(i == 2) type = "operator";
		else if(i == 3) type = "manager";
		
		return type;
	}

}

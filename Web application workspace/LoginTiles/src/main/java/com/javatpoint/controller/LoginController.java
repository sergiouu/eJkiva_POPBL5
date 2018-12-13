package com.javatpoint.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.javatpoint.form.Contact;

import probeHibernate.User;
import utils.HibernateUtils;

@Controller  
@SessionAttributes 
public class LoginController {
	
	@RequestMapping("/login")  
    public String login(Model m, WebRequest request) {  
        m.addAttribute("command", new User());
        String u = request.getParameter("uname");
        String p = request.getParameter("password");
        

        
        System.out.println(u + " "+p);
         if(u!=null && p!=null) {
        	 if(consult(u, p)) {
            	System.out.println("ENTER");
            	return "hello";
            }else System.out.println("FAIL");
        	
        	;
        }
            
        
        return "login";  
    }  
	
	public static boolean consult(String name, String password) {
		String uType;
		
		Session session=HibernateUtils.getSessionFactory().openSession();
		Query<User> query=session.createSQLQuery("SELECT * FROM user where uName = '"+name+ "' and password ='"+password+"'").addEntity(User.class);
		//List<User> users=query.list();
		
		User user=query.uniqueResult();
		
		if(user==null) {
			System.out.println("Ez du balio");
			return false;
		}else {
			System.out.println(user.getUserTypeId());
			if(user.getUserTypeId()==1) {uType="customer";}
			else if(user.getUserTypeId()==2) {uType="operator";}
			else if(user.getUserTypeId()==3) {uType="manager";}
			return true;
		}
		
		
		//System.out.println("Hau da nire zenbakia"+numb);
		

	 }

	@RequestMapping(value = "/register", method = RequestMethod.POST)  
    public String register(@ModelAttribute("user") User user, BindingResult result) {  
        //write the code here to add contact  
        return "redirect:contact.html";  
    }
}

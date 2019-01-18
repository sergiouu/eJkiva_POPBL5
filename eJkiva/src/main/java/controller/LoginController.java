package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import repository.UserRepository;
import utils.HibernateUtils;

/**
 * Login class.
 * @class LoginController
 * @author Leire
 *
 */

@Controller  
@SessionAttributes 
public class LoginController {
		
	UserRepository repo;
	/**
     * This method will login or register a user and redirect them depending on their type 
	 * @throws Exception 
     */
	@RequestMapping("/login")  
    public String login(Model m, HttpServletResponse response, HttpServletRequest request, WebRequest wrequest) throws Exception {  
        m.addAttribute("command", new User());
        repo = new UserRepository();
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
					user = repo.checkUser(username, password);
					System.out.println("USER "+user);
				}
					
				//Save login in session
				if(user != null){
					System.out.println("Username:"+user.getUsername());
					session.setAttribute("user",user);
					request.setAttribute("message", "login.successful");
					Usertype usertype = repo.checkUsertype(user);
					response.sendRedirect("/eJkiva/"+usertype.getUsertype().toLowerCase()+".html");
			        break; 
				}else{
					System.out.println("No user loaded");
					session.setAttribute("user", null);
					request.setAttribute("errorL", "Wrong username ("+username+") or password ("+password+").");//"login.uperror");
					request.setAttribute("username", username);
					request.setAttribute("password", password);
			        break; 
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
				Usertype utype = repo.getUsertypeById(1);
				if(uname==null||name==null||surname==null||email==null||borndate==null||pwd==null||passwordRepeat==null) {
					System.out.println("Empty input!");
					request.setAttribute("errorR", "Empty field!");
					request.setAttribute("register", true);
			        break; 
				}
				else if(pwd.equals(passwordRepeat)) {
					User newUser = repo.addUser(utype, uname, pwd, name, surname, email, borndate);
					session.removeAttribute("user");
					session.setAttribute("user",newUser);
					request.setAttribute("message", "register.successful");
					response.sendRedirect("/eJkiva/customer.html");
				}else {
					System.out.println("Passwords don't match!");
					request.setAttribute("errorR", "Passwords don't match!");
					request.setAttribute("register", true);
			        break; 
				}
				
			case "logout":
				session.removeAttribute("user");
				session.removeAttribute("cart");
				session.invalidate();
				break;
		} 
		return "login";
    }  	
	
	
	
	
}

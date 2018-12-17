package com.javatpoint.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.sql.Date;

public class LoginControllerTest {

	LoginController lc;
	
	@Before
    public void before() {
        lc = new LoginController();
    }

    @After
    public void after() {
    	lc = null;
    }
    
    @Test
    public void testLogin() throws Exception{


    }
    
    @Test
    public void testConsultCorrectUser() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException{
    	//Find a method in the class that is called division and has two int parameters
        Method method = LoginController.class.getDeclaredMethod("consult",
                String.class, String.class);
        //Make the method accesible on execution
        method.setAccessible(true);
        //Invoke the method
        boolean result = (boolean)method.invoke(lc, "Manexzini", "eskola");
        //Check the results
        assertEquals("The username and password are correct.", true , result);

    }
	
    @Test
    public void testConsultWrongUser() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException{
    	//Find a method in the class that is called division and has two int parameters
        Method method = LoginController.class.getDeclaredMethod("consult",
                String.class, String.class);
        //Make the method accesible on execution
        method.setAccessible(true);
        //Invoke the method
        boolean result = (boolean)method.invoke(lc, "wrongusername", "pwd");
        //Check the results
        assertEquals("The username and password are correct.", false , result);

    }
    
    
    @Test
    public void testRegister() throws Exception{


    }
	
    
    @Test
    public void testTransformDateOK() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException, ParseException {
        //Find a method in the class that is called division and has two int parameters
        Method method = LoginController.class.getDeclaredMethod("transformDate",
                String.class);
        //Make the method accesible on execution
        method.setAccessible(true);
        //Invoke the method
        Date result = (Date)method.invoke(lc, "22/09/1998");
        //Check the results
        boolean instance = false;
        if(result instanceof Date) instance = true;
        assertEquals("The result must be of type date.", true , instance);

    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testTransformDateFails() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException, ParseException {
        //Find a method in the class that is called division and has two int parameters
        Method method = LoginController.class.getDeclaredMethod("transformDate",
                String.class);
        //Make the method accesible on execution
        method.setAccessible(true);
        //Invoke the method
        Date result = (Date)method.invoke(lc, "22-09-1998");
        //Check the results
        boolean instance = false;
        if(result instanceof Date) instance = true;
        assertEquals("The result must be of type date.", false , instance);
        
        thrown.expect(ParseException.class);
        thrown.expectMessage("Inserted value incorrect format.");

    }
	
}

package customer;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import controller.CustomerController;
import entity.Product;
import repository.CustomerRepository;

public class CustomerTest {

	@Test
    public void testAddProductToCart() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException {
        
        
    }
	
	@Test
    public void testGetTotalCart() throws ClassNotFoundException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, InstantiationException,
    NoSuchMethodException, SecurityException {
		List<Integer> nums = new ArrayList<>();
		for(int i=0; i<10; i++) {
			nums.add(i);
		}
        //Find a method in the class that is called division and has two int parameters
        Method method = CustomerController.class.getDeclaredMethod("getTotalCart");
        //Make the method accesible on execution
        method.setAccessible(true);
        //Invoke the method
        Integer result = (Integer)method.invoke(new CustomerController());
        //Check the results
        assertEquals("La division debe ser 45", 45,(Object)result);
        
    }
	
	
	
}

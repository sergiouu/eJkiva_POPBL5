package eJkiva_PBL5Sim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import principal.Principal;
import producto.AdministradorDeProductos;

public class TestPrincipal {

	Circuito circuito;
	AdministradorDeProductos ap;

	@Before
	public void before() {
		this.circuito = new Circuito();
		this.ap = new AdministradorDeProductos(circuito);
	}
	
	@Test
	public void principalTest() {
		equals(ap);
	}

	@Test
	public void testMain() throws Exception{
		System.out.println("main");
		String[] args = null;
		principalTest();
		System.setIn(System.in);
		Principal.main(args);
		System.setIn(System.in);
	}
	

	@After
	public void after() {
		System.setOut(System.out);
	}
}

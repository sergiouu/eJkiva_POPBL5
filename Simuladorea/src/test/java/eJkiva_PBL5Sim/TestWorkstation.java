package eJkiva_PBL5Sim;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import producto.Producto;


public class TestWorkstation {

	Circuito circuito;
	Workstation workstation;
	Robot robot;
	Producto producto;
	
	@Before
	public void before() {
		this.circuito = new Circuito();
		this.workstation = circuito.getWorkstations().get(0);
		this.robot = circuito.getRobots().get(0);
	}
	
	@Test
	public void getInsideEsperaTest() {
		assertEquals(workstation.getInsideEspera(), workstation.getInsideEspera());
	}
	
	@Test
	public void getOutsideEsperaTest() {
		assertEquals(workstation.getOutsideEspera(), workstation.getOutsideEspera());
	}
	
	@Test
	public void getIdTest() {
		assertEquals(workstation.getId(), workstation.getId());
	}
	
	@Test
	public void getPosXTest() {
		assertEquals(workstation.getPosX(), workstation.getPosX());
	}
	
	@Test
	public void getPosYTest() {
		assertEquals(workstation.getPosY(), workstation.getPosY());
	}
	
	@Test
	public void isStateInsideTest() {
		assertEquals(workstation.isStateInside(), false);
	}
	
	@Test
	public void isStateOutsideTest() {
		assertEquals(workstation.isStateOutside(), false);
	}
	
	@Test
	public void getInsideTest() {
		assertEquals(workstation.getInside(), workstation.getInside());
	}
	
	@Test
	public void getOutsideTest() {
		assertEquals(workstation.getOutside(), workstation.getOutside());
	}
	
	@Test
	public void getListaProductos() {
		assertEquals(workstation.getListaProductos(), workstation.getListaProductos());
	}
	
	@After
	public void after() {
        System.setOut(System.out);
	}
}

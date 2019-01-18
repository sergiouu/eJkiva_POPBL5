package eJkiva_PBL5Sim;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import producto.Producto;

public class TestRobot {

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
	public void getWsCogerProductoTest() {
		assertEquals(robot.getWsCogerProducto(), robot.getWsCogerProducto());
	}
	
	@Test
	public void descargarProductoTest() {
		assertEquals(robot.getProducto(), null);
	}
	
	@Test
	public void getProductoTest() {
		assertEquals(robot.getProducto(), robot.getProducto());
	}
	
	@Test
	public void cargarProductoTest() {
		assertEquals(robot.getProducto(), producto);
	}
	
	@Test
	public void getIdTest() {
		assertEquals(robot.getId(), robot.getId());
	}
	
	@Test
	public void getWsActualTest() {
		assertEquals(robot.getWsActual(), robot.getWsActual());
	}
	
	@Test
	public void getWsDestinoTest() {
		assertEquals(robot.getWsDestino(), robot.getWsDestino());
	}
	
	@Test
	public void getWsSiguienteTest() {
		assertEquals(robot.getWsSiguiente(), robot.getWsSiguiente());
	}
	
	@Test
	public void getWorkstationVacioTest() {
		assertEquals(robot.getWorkstationVacio(), robot.getWsDestino());
	}
	
	@Test
	public void getWorkstationByIdTest() {
		assertEquals(robot.getWorkstationById(workstation.getId()), robot.getWorkstationById(workstation.getId()));
	}
	
	@After
	public void after() {
        System.setOut(System.out);
	}
}

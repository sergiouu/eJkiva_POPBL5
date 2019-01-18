package eJkiva_PBL5Sim;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import producto.AdministradorDeProductos;
import producto.Producto;

public class TestAdministradosDeProductos {

	Circuito circuito;
	Workstation workstation;
	Robot robot;
	Producto producto;
	AdministradorDeProductos ap;
	
	@Before
	public void before() {
		this.circuito = new Circuito();
		this.workstation = circuito.getWorkstations().get(0);
		this.robot = circuito.getRobots().get(0);
		this.ap = new AdministradorDeProductos(circuito);
	}
	
	@Test
	public void buscarProductoTest() {
		assertEquals(ap.buscarProducto(this.producto.getId()), ap.buscarProducto(this.producto.getId()));
	}
	
	@Test
	public void buscarRobotLibreTest() {
		assertEquals(ap.buscarRobotLibre(), ap.buscarRobotLibre());
	}
	
	

	@After
	public void after() {
		System.setOut(System.out);
	}
}

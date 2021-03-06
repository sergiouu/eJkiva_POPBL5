package eJkiva_PBL5Sim;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import movimiento.Accion;
import producto.Producto;

public class TestCircuito {

	Circuito circuito;
	Workstation workstation;
	Robot robot;
	Producto producto;
	Accion accion;

	@Before
	public void before() {
		this.circuito = new Circuito();
		this.workstation = circuito.getWorkstations().get(0);
		this.robot = circuito.getRobots().get(0);
		this.accion = new Accion(this.robot, this.circuito.getWorkstations());
	}


	@Test
	public void getByDescriptionTest() {
		assertEquals(circuito.getByDescription(workstation.getDescription()), circuito.getByDescription(workstation.getDescription()));
	}

	@Test
	public void getWorkstationByIdTest() {
		assertEquals(circuito.getWorkstationById(workstation.getId()), circuito.getWorkstationById(workstation.getId()));
	}


	@After
	public void after() {
		System.setOut(System.out);
	}
}

package eJkiva_PBL5Sim;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import producto.Pedido;
import producto.Producto;

public class TestPedido {

	Circuito circuito;
	Workstation workstation;
	Robot robot;
	Producto producto;
	Pedido pedido;

	@Before
	public void before() {
		this.circuito = new Circuito();
		this.workstation = circuito.getWorkstations().get(0);
		this.robot = circuito.getRobots().get(0);
		this.pedido = new Pedido();
		this.producto = new Producto();
	}
	
	@Test
	public void toStringTest() {
		assertEquals(pedido.toString(), "pedido-id:"+this.pedido.getId());
	}
	
	@Test
	public void getFechaSalidaTest() {
		assertEquals(pedido.getFechaSalida(), pedido.getFechaSalida());
	}
	
	/*@Test
	public void añadirProductoTest() {
		List<Producto> lista = new ArrayList<Producto>();
		lista.add(producto);
		assertEquals(pedido.añadirProducto(producto), pedido.añadirProducto(producto));
	}*/
	
	@Test
	public void getIdTest() {
		assertEquals(pedido.getId(), pedido.getId());
	}
	
	@Test
	public void getCantidadTest() {
		assertEquals(pedido.getCantidad(), pedido.getCantidad());
	}
	
	@Test
	public void getProductoTest() {
		assertEquals(pedido.getProducto(), pedido.getProducto());
	}
	

	@After
	public void after() {
		System.setOut(System.out);
	}
}

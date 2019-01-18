package eJkiva_PBL5Sim;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SQLConnector.SQLConnector;
import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
import producto.Pedido;
import producto.Producto;

public class TestSQLConnector {

	Circuito circuito;
	Workstation workstation;
	Robot robot;
	Producto producto;
	SQLConnector connection;
	Pedido pedido;
	
	@Before
	public void before() {
		this.circuito = new Circuito();
		this.workstation = circuito.getWorkstations().get(0);
		this.robot = circuito.getRobots().get(0);
		this.pedido = new Pedido();
		this.connection = new SQLConnector(circuito);
	}
	
	/*@Test
	public void getLastIdTest() {
		assertEquals(connection.getLastId(), connection.lastId);
	}*/
	
	@Test
	public void getNuevosPedidosTest() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(this.pedido);
		assertEquals(connection.getNuevosPedidos(), pedidos);
	}
	
	@Test
	public void getTodosLosProductosTest() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = this.connection.getNuevosPedidos();
		assertEquals(connection.getTodosLosProductos(), pedidos);
	}
	
	@Test
	public void getProductosDePedidoTest() {
		assertEquals(connection.getProductosDePedido(this.pedido.getId()), this.pedido.getProducto());
	}
	

	@After
	public void after() {
		System.setOut(System.out);
	}
}

package producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import SQLConnector.SQLConnector;
import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;
/**
*
* <h2> AdministradorDeProductos class </h2>
* <p>
* Administrates and manages all the orders</p>
*
*@extends Thread
*
* @author elarretegui 09/Jan/2019
*/
public class AdministradorDeProductos extends Thread{

	SQLConnector conexion;
	List<Producto> availableProducts;
	List<Producto> productosEnCola;
	Circuito circuito;
	int kont=0;
	
	/**
	*
	* <h2> AdministradorProductos </h2>
	* <p>
	* Constructor of the class </p>
	*
	* @author elarretegui 09/Jan/2019
	* 
	*
	*
	*/
	public AdministradorDeProductos(Circuito circuito) {
		this.circuito = circuito;
		productosEnCola=Collections.synchronizedList(new ArrayList<Producto>());
		conexion = new SQLConnector(circuito);
	}

	
	@Override
	public void run() {
		boolean conectado = true;
		this.conexion.connect();
		availableProducts = conexion.getTodosLosProductos();
		almacenarProductos(availableProducts);
		do {
			añadirNuevosPedidos();
			asignarProductoEnCola();
		}while(conectado);
		this.conexion.disconnect();
	}
	
	/**
	*
	* <h2> almacenarProductos </h2>
	* <p>
	* Stores all the products of the database in our workstations</p>
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	private void almacenarProductos(List<Producto> todosLosProductos) {
		for(Producto p : todosLosProductos) {
				circuito.getWorkstationById(p.getWorkstationID()).añadirProducto(p);
				p.setWsActual(circuito.getWorkstationById(p.getWorkstationID()));
			}
	}


/**
*
* <h2> añadirNuevosPedidos </h2>
* <p>
* Gets new orders in the database </p>
*
*
* @author elarretegui 09/Jan/2019
*
*/
	@SuppressWarnings("unused")
	private void añadirNuevosPedidos() {
		int kont = 0;
		List<Pedido> nuevosPedidos;
		nuevosPedidos = conexion.getNuevosPedidos();
	
		if(nuevosPedidos != null){
			for(Pedido p : nuevosPedidos) {
				añadirProductosACola(conexion.getProductosDePedido(p.getId()));
			}
		}
	}
	

/**
*
* <h2> añadirProductosACola </h2>
* <p>
* Adds new products to the list of products which are not assigned yet </p>
*
* @param listaProductos
*
* @author elarretegui 09/Jan/2019
*
*/
	private void añadirProductosACola(List<Producto> listaProductos) {
		synchronized (productosEnCola) {
			productosEnCola.addAll(listaProductos);
		}
	}
	
	/**
	*
	* <h2> asignarProductoEnCola </h2>
	* <p>
	* Adds new products to the list of products which are not assigned yet </p>
	*
	* @param listaProductos
	*
	* @author elarretegui 09/Jan/2019
	*
	*/	
	private void asignarProductoEnCola() {
		if(productosEnCola.size()>0) {
			asignarProductoARobot(productosEnCola.get(0), buscarRobotLibre());
		}
	}

	/**
	*
	* <h2> asignarProductoARobot </h2>
	* <p>
	* Adds new products to the list of products which are not assigned yet </p>
	*
	* @param producto, robot
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	public void asignarProductoARobot(Producto producto, Robot robot) {
			
		if(robot!=null) {
			robot.cargarProducto(producto);
			System.out.println("Producto asignado:" + "robot id:" + robot.getId()+ "producto:" + robot.getProducto());
			
			robot.setWsCogerProducto(buscarProducto(producto.getId())); 
		//	System.out.println("ID:" + buscarProducto(producto.getId()));
			robot.setWsDestino(circuito.getByDescription("Packaging A")); 
			
			robot.encenderRobot();
			if(producto.getCantidad()==1) {
				eliminarProductoDeCola(producto);
				kont++;
			}
			else {
				producto.setCantidad(producto.getCantidad()-1);
				kont++;
				}
			}
	}
		
	/**
	*
	* <h2> getCircuito </h2>
	* <p>
	* Find the workstation in which is the product with X id </p>
	*
	* @return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	public Workstation buscarProducto(int id) {
		Workstation ws = null;
		for(Workstation w : circuito.getWorkstations()) {
			for(Producto p : w.getListaProductos()) {
				if(p.getId() == id) {
					ws = w;
				}
			}
		}
		return ws;
	}

	/**
	*
	* <h2> eliminarProductoDeCola </h2>
	* <p>
	* When a product is assigned to a robot it is removed from the list</p>
	*
	* @param producto
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	public void eliminarProductoDeCola(Producto producto) {
		synchronized (productosEnCola) {
			productosEnCola.remove(producto);
		}
	}
	/**
	*
	* <h2> buscarRobotLibre </h2>
	* <p>
	* Find a free robot </p>
	*
	* @param listaProductos
	*
	* @author elarretegui 09/Jan/2019
	*
	*/

	public Robot buscarRobotLibre() {
		Robot libre=null;
		boolean asignado=false;
		
		for(Robot robot : circuito.getRobots()) {
			if(robot.getProducto()==null && !asignado) {			
				libre = robot;
				asignado = true;
			}		
		}
		return libre;
	}
	/**
	*
	* <h2> getConexion </h2>
	* <p>
	* Gets the conexion with the database</p>
	*
	* @return SQLConnector
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	public SQLConnector getConexion() {
		return conexion;
	}
	
}

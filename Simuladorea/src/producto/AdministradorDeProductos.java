package producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import SQLConnector.SQLConnector;
import circuito.Circuito;
import circuito.Robot;
import circuito.Workstation;

public class AdministradorDeProductos extends Thread{

	SQLConnector conexion;
	List<Producto> availableProducts;
	List<Producto> productosEnCola;
	List<Pedido> listaPedidos;
	Circuito circuito;
	int kont=0;
	
	public AdministradorDeProductos(Circuito circuito) {
		this.circuito = circuito;
		productosEnCola=Collections.synchronizedList(new ArrayList<Producto>());
		listaPedidos=Collections.synchronizedList(new ArrayList<Pedido>());
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

	private void almacenarProductos(List<Producto> todosLosProductos) {
		for(Producto p : todosLosProductos) {
				circuito.getWorkstationById(p.getWorkstationID()).añadirProducto(p);
				p.setWsActual(circuito.getWorkstationById(p.getWorkstationID()));
			}
	}

	@SuppressWarnings("unused")
	private void añadirNuevosPedidos() {
		int kont = 0;
		List<Pedido> nuevosPedidos;
		nuevosPedidos = conexion.getNuevosPedidos();
		
		if(nuevosPedidos != null){
			añadirPedidosALista(nuevosPedidos);
			System.out.println(nuevosPedidos);
			for(Pedido p : nuevosPedidos) {
				conexion.obtenerCantidadProductosEnPedido(p);
				añadirProductosACola(conexion.getProductosDePedido(p.getId()));
			}
		}
	}
	
	private void añadirProductosACola(List<Producto> listaProductos) {
		synchronized (productosEnCola) {
			this.productosEnCola.addAll(listaProductos);
		}
	}
	public void añadirPedidosALista(List<Pedido> listapedidos) {
		synchronized (listaPedidos) {
			this.listaPedidos.addAll(listapedidos);
		}
	}
	
	public void eliminarPedidosDeLista(Pedido p) {
		synchronized (listaPedidos) {
			listaPedidos.remove(p);
		}
		System.out.println("LISTA:"+listaPedidos);
	}
	
	
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	private void asignarProductoEnCola() {
		if(productosEnCola.size()>0) {
			asignarProductoARobot(productosEnCola.get(0), buscarRobotLibre());
		}
	}

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
		
	private Workstation buscarProducto(int id) {
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

	public void eliminarProductoDeCola(Producto producto) {
		synchronized (productosEnCola) {
			productosEnCola.remove(producto);
		}
	}

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

	public SQLConnector getConexion() {
		return conexion;
	}
	
	
}

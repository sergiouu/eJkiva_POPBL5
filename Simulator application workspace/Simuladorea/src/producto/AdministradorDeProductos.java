package producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import circuito.Circuito;
import objeto.Robot;

public class AdministradorDeProductos extends Thread{
	int kont =0;
	//SQLconnector conexion;
	@Override
	public void run() {
	//	boolean encendido = true;
		int encendido= 0;
		añadirProductos();
		do {
		//producto = funcion comprobar productos nuevos y añadirlos a la lista
	//	añadirProductos();
			if(productosEnCola.size()>0) {
				asignarProductoARobot(productosEnCola.get(0), buscarRobotLibre());
			}
		}while(true);
	}

	public void eliminarProducto(Producto eliminarProducto) {
		for (Iterator<Producto> it = productosEnCola.iterator(); it.hasNext(); ) {
		    Producto p = it.next();
		    if (p.equals(eliminarProducto)) {
		        it.remove();
		    }
		}
	}
	
	
	private void añadirProducto(Producto p) {
		productosEnCola.add(p);
	}

	List<Producto> productosEnCola; //pasar a sincronizada para que no pete
	Circuito circuito;
	
	public AdministradorDeProductos(Circuito circuito) {
		this.circuito = circuito;
		productosEnCola=Collections.synchronizedList(new ArrayList<Producto>());
	}

	private void añadirProductos() {
		
		if(kont==0) {
		añadirProducto(new Producto (1,"iphone", circuito.getByDescription("Packaging A"), circuito.getByDescription("Order Exit")));
		añadirProducto(new Producto (2,"samsung",circuito.getByDescription("Packaging A"), circuito.getByDescription("Order Exit")));
		
		Producto p; 
		p = new Producto (5,"pantalon", circuito.getByDescription("Packaging A"), circuito.getByDescription("Order Exit"));
		añadirProducto(p);

		p = new Producto (6, "movil", circuito.getByDescription("Packaging A"), circuito.getByDescription("Order Exit"));
		añadirProducto(p);
		
		p = new Producto (3, "psp", circuito.getByDescription("Packaging A"), circuito.getByDescription("Order Exit"));
		añadirProducto(p);
		
		//p = new Producto (4, "boli", circuito.getByDescription("Packaging A"), circuito.getByDescription("Packaging D"));
		//añadirProducto(p);
		
		System.out.println("Products added");
		kont++;
		
		}
		
	}

	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}


	
	public void asignarPaqueteAWorkstation(Producto producto) {
		
		
		//	System.out.println("ROBOT DESIGNADO PARA PAQUETE: "+producto.getId()+" ES EL ROBOT: "+producto.getRobot());
			
			switch(producto.getWsActual().getId()) {
			case 1:
				circuito.getWorkstations().get(0).añadirProducto(producto); 
				break;
			case 2:
				circuito.getWorkstations().get(1).añadirProducto(producto); 
				break;
			case 3:
				circuito.getWorkstations().get(2).añadirProducto(producto); 
				break;
			case 4:
				circuito.getWorkstations().get(3).añadirProducto(producto); 
				break;
			case 5:
				circuito.getWorkstations().get(4).añadirProducto(producto); 
				break;
			case 6:
				circuito.getWorkstations().get(5).añadirProducto(producto); 
				break;
			default:
				break;
			}	
		
	}

	public boolean asignarProductoARobot(Producto producto, Robot robot) {
		
		boolean libre = false;
		
		if(robot!=null) {
			robot.cargarProducto(producto);
			System.out.println("Producto asignado:" + "robot id:" + robot.getId()+ "producto:" + robot.getProducto().getDescription());
			robot.setWsCogerProducto(producto.wsActual); //CAMBIAR
			robot.setWsDestino(producto.wsDestino); //CAMBIAR
			libre = true;
			robot.encenderRobot();
			eliminarProducto(producto);
			productosEnCola.remove(producto);
			}
		else {
			libre=false;
		}
		return libre;
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
	
	public void eliminarProductoDeCola(Producto producto) {
		productosEnCola.remove(producto);
	}
}

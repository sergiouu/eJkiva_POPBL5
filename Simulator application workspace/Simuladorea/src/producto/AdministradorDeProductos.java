package producto;
import java.util.ArrayList;
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
		do {
		//producto = funcion comprobar productos nuevos y añadirlos a la lista
		añadirProductos();
		encendido ++;
		}while(encendido<10);
	}

	private void añadirProducto(Producto p) {
		productosEnCola.add(p);
		if(asignarProductoARobot(productosEnCola.get(0))) {
			productosEnCola.remove(0);
		}
		System.out.println("cola size:" + productosEnCola);
	}

	List<Producto> productosEnCola; //pasar a sincronizada para que no pete
	Circuito circuito;
	
	public AdministradorDeProductos(Circuito circuito) {
		this.circuito = circuito;
		productosEnCola=new ArrayList<>();
	}

	private void añadirProductos() {
		
		if(kont==0) {
		añadirProducto(new Producto (1,"iphone", circuito.getByDescription("Product Entry"), circuito.getByDescription("Packaging A")));
		añadirProducto(new Producto (2,"samsung", circuito.getByDescription("Product Entry"),circuito.getByDescription("Packaging A")));
		
		Producto p; 
		p = new Producto (5,"pantalon", circuito.getByDescription("Product Entry"), circuito.getByDescription("Packaging A"));
		añadirProducto(p);

		p = new Producto (6, "movil", circuito.getByDescription("Product Entry"), circuito.getByDescription("Packaging A"));
		añadirProducto(p);
		
		p = new Producto (3, "psp", circuito.getByDescription("Product Entry"), circuito.getByDescription("Packaging A"));
		añadirProducto(p);
		
		p = new Producto (4, "boli", circuito.getByDescription("Product Entry"), circuito.getByDescription("Packaging A"));
		añadirProducto(p);
		
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
		
			asignarProductoARobot(producto);

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

	public boolean asignarProductoARobot(Producto producto) {
		Robot robot;
		boolean libre = false;
		
		robot = buscarRobotLibre();
		if(robot!=null) {
			robot.cargarProducto(producto);
			System.out.println("Producto asignado:" + "robot id:" + robot.getId()+ "producto:" + robot.getProducto().getDescription());
			robot.setWsCogerProducto(producto.wsDestino); //CAMBIAR
			robot.setWsDestino(circuito.getByDescription("Order Exit")); //CAMBIAR
			libre = true;
			robot.encenderRobot();
			}
		else {
			libre=false;
			System.out.println("no hay robots libres");
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
		System.out.println("libre");
		return libre;
	}	
	
	public void eliminarProductoDeCola(Producto producto) {
		productosEnCola.remove(producto);
	}
}

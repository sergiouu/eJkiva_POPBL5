package Producto;
import java.util.ArrayList;

import Circuito.Circuito;
import Circuito.Producto;
import Circuito.Workstation;
import Objeto.Robot;

public class AdministradorDeProductos extends Thread{

	@Override
	public void run() {
		
	}

	public ArrayList<Producto> productosEnCola;
	public ArrayList<Workstation> listaWorkstations;
	public ArrayList<Robot> listaRobots;
	Circuito circuito;
	
	public AdministradorDeProductos() {
		
		this.listaWorkstations=new ArrayList<Workstation>();
		productosEnCola=new ArrayList<Producto>();
		listaRobots=new ArrayList<Robot>();
		añadirProdutos(); //para hacer una prueba
	}

	private void añadirProdutos() {
		Producto p; 
		p = new Producto (1,"pantalon", circuito.getWorkstationById(2));
		productosEnCola.add(p);
		p = new Producto (2, "movil", circuito.getWorkstationById(2));
		productosEnCola.add(p);
		p = new Producto (3, "psp", circuito.getWorkstationById(2));
		productosEnCola.add(p);
		p = new Producto (4, "boli", circuito.getWorkstationById(2));
		productosEnCola.add(p);
		
	}

	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
		actualizarWorkstations();
	}

	private void actualizarWorkstations() {
		this.listaWorkstations = circuito.getWorkstations();
	}
	
	private void asignarPaqueteAWorkstation(Producto producto) {
		
		buscarRobotLibre(producto);

			System.out.println("ROBOT DESIGNADO PARA PAQUETE: "+producto.getId()+" ES EL ROBOT: "+producto.getRobot());
			
			switch(producto.getWsActual().getId()) {
			case 1:
				listaWorkstations.get(0).añadirProducto(producto); 
				break;
			case 2:
				listaWorkstations.get(1).añadirProducto(producto);
				break;
			case 3:
				listaWorkstations.get(2).añadirProducto(producto);
				break;
			case 4:
				listaWorkstations.get(3).añadirProducto(producto);
				break;
			case 5:
				listaWorkstations.get(4).añadirProducto(producto);
				break;
			case 6:
				listaWorkstations.get(5).añadirProducto(producto);
				break;
			}	
		
	}

	private void buscarRobotLibre(Producto producto) {
		boolean assigned= false;
		for(Robot robot : listaRobots) {
			if(robot.getProducto()==null && assigned == false) {			
				robot.asignaProductoARobot(producto);
				producto.setRobot(robot);
				assigned = true;
			}		
		}	
	}
	
	public void eliminarProductoDeCola(Producto producto) {
		productosEnCola.remove(producto);
	}
}

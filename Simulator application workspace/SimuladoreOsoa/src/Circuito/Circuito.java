package Circuito;
import java.util.ArrayList;

import Objeto.Robot;
import Producto.AdministradorDeProductos;

public class Circuito {
	
	ArrayList<Workstation> listaWorkstation;
	ArrayList<Robot> listaRobot;
	AdministradorDeProductos ap;	
	public Circuito(AdministradorDeProductos ap) {
		listaWorkstation = new ArrayList<>();
		listaRobot = new ArrayList<>();
		this.ap=ap;
		initWorkstations();
		initRobots();
		
	}
	
	public void addRobots(Robot robot) {		
		listaRobot.add(robot);		
	}
	
	public void addWorkstation(Workstation workstation) {		
		listaWorkstation.add(workstation);		
	}
	
	public void initWorkstations() {
		Workstation workstation;
		
		workstation=new Workstation(1,0, 0, "Order Exit");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(2,2, 0, "Product Entry");
		listaWorkstation.add(workstation);

		workstation=new Workstation(3,0, 1, "Packaging A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(4,2, 1, "Packaging B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(5,4, 1, "Packaging C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(6,4, 0, "Packaging D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(7,1, 1, "Parking A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(8,3, 1, "Parking B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(9,3, 0, "Parking C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(10,1, 0, "Parking D");
		listaWorkstation.add(workstation);
		
	}
	
	public void initRobots() {
		Robot robot;
		
		robot=new Robot(1,1,1,"Robot A",ap);
		listaRobot.add(robot);
		
		robot=new Robot(2,3,1,"Robot B",ap);
		listaRobot.add(robot);
		
		robot=new Robot(3,3,0,"Robot C",ap);
		listaRobot.add(robot);
		
		robot=new Robot(4,1,0,"Robot D",ap);
		listaRobot.add(robot);
		
		robot=new Robot(5,2,0,"Robot E",ap);
		listaRobot.add(robot);
	}
	
	public ArrayList<Robot> getRobots(){
		ArrayList<Robot> copia = listaRobot;
		return copia;
	}
	
	public ArrayList<Workstation>  getWorkstations(){
		ArrayList<Workstation> copia = listaWorkstation;
		return copia;
	}
	
	public Workstation getWorkstationById(int id) {
		Workstation ws= null;
		for(Workstation w : listaWorkstation) {
			if(w.getId() == id) {
				ws = w;
			}
		}
		return ws;
	}

}
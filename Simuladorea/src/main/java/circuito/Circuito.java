package circuito;
import java.util.ArrayList;
import java.util.List;

import SQLConnector.SQLConnector;
import producto.AdministradorDeProductos;

public class Circuito {
	
	List<Workstation> listaWorkstation;
	List<Robot> listaRobot;
	AdministradorDeProductos ap;
	SQLConnector conexion;
	
	public Circuito() {
		listaWorkstation = new ArrayList<Workstation>();
		listaRobot = new ArrayList<Robot>();
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
		
		workstation=new Workstation(3,2, 0, "Product Entry");
		listaWorkstation.add(workstation);

		workstation=new Workstation(12,0, 1, "Packaging A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(10,2, 1, "Packaging B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(8,4, 1, "Packaging C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(7,5, 1, "Packaging D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(11,1, 1, "Parking A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(9,3, 1, "Parking B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(4,3, 0, "Parking C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(2,1, 0, "Parking D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(6,5, 0, "Packaging E");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(5,4, 0, "Packaging F");
		listaWorkstation.add(workstation);
		
		
		System.out.println("Workstations created");
	}
	
	public void initRobots() {
		Robot robot1, robot2, robot3, robot4, robot5;
		
		robot1=new Robot(1,"Robot A", this.getByDescription("Parking A"), listaWorkstation);
		this.getByDescription("Parking A").setInside(robot1);
		listaRobot.add(robot1);
		robot1.start();
		
		robot2=new Robot(2,"Robot B",this.getByDescription("Parking B"), listaWorkstation);
		this.getByDescription("Parking B").setInside(robot2);
		listaRobot.add(robot2);
		robot2.start();
		
		robot3=new Robot(3,"Robot C",this.getByDescription("Parking C"), listaWorkstation);
		this.getByDescription("Parking C").setInside(robot3);
		listaRobot.add(robot3);
		robot3.start();
		
		robot4=new Robot(4,"Robot D", this.getByDescription("Parking D"), listaWorkstation);
		this.getByDescription("Parking D").setInside(robot4);
		listaRobot.add(robot4);
		robot4.start();
		
		robot5=new Robot(5,"Robot E", this.getByDescription("Packaging C"), listaWorkstation);
		this.getByDescription("Packaging C").setInside(robot5);
		listaRobot.add(robot5);
		robot5.start();
		
		System.out.println("Robots created");
	}
		
	public Workstation getByDescription(String description) {
		Workstation ws = null;
		for(Workstation w : listaWorkstation) {
			if(w.getDescription().equals((description))) {
				ws=w;
			}
		}
		return ws;
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

	public void setAp(AdministradorDeProductos ap) {
		this.ap = ap;
		for(Robot r : listaRobot) {
			r.setAp(ap);
		}
	}

	public List<Robot> getRobots(){
		List<Robot> copia = listaRobot;
		return copia;
	}
	
	public List<Workstation>  getWorkstations(){
		List<Workstation> copia = listaWorkstation;
		return copia;
	}
	

}
package circuito;
import java.util.ArrayList;
import java.util.List;

import objeto.Robot;

public class Circuito {
	
	List<Workstation> listaWorkstation;
	List<Robot> listaRobot;
	
	public Circuito() {
		listaWorkstation = new ArrayList<>();
		listaRobot = new ArrayList<>();
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
		
		System.out.println("Workstations created");
	}
	
	public void initRobots() {
		Robot robot;
		
		robot=new Robot(1,"Robot A", this.getByDescription("Parking A"));
		System.out.println(this.getByDescription("Parking A"));
		listaRobot.add(robot);
		
		robot=new Robot(2,"Robot B",this.getByDescription("Parking B"));
		this.getByDescription("Parking B").stateInside = true;

		listaRobot.add(robot);
		
		robot=new Robot(3,"Robot C",this.getByDescription("Parking C"));
		this.getByDescription("Parking C").stateInside = true;

		listaRobot.add(robot);
		
		robot=new Robot(4,"Robot D", this.getByDescription("Parking D"));
		this.getByDescription("Parking D").stateInside = true;

		listaRobot.add(robot);
		
		robot=new Robot(5,"Robot E", this.getByDescription("Product Entry"));
		this.getByDescription("Product Entry").stateInside = true;

		listaRobot.add(robot);
		
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
	public List<Robot> getRobots(){
		List<Robot> copia = listaRobot;
		return copia;
	}
	
	public List<Workstation>  getWorkstations(){
		List<Workstation> copia = listaWorkstation;
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
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
		
		workstation=new Workstation(3,2, 0, "Product Entry");
		listaWorkstation.add(workstation);

		workstation=new Workstation(10,0, 1, "Packaging A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(8,2, 1, "Packaging B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(6,4, 1, "Packaging C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(5,4, 0, "Packaging D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(9,1, 1, "Parking A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(7,3, 1, "Parking B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(4,3, 0, "Parking C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(2,1, 0, "Parking D");
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
		this.getByDescription("Parking C").setInside(robot2);
		listaRobot.add(robot2);
		robot2.start();
		System.out.println("robots added" + listaRobot.size());
		
		robot3=new Robot(3,"Robot C",this.getByDescription("Parking C"), listaWorkstation);
		this.getByDescription("Parking C").setInside(robot3);
		listaRobot.add(robot3);
		robot3.start();
		
		robot4=new Robot(4,"Robot D", this.getByDescription("Parking D"), listaWorkstation);
		this.getByDescription("Parking D").setInside(robot4);
		listaRobot.add(robot4);
		robot4.start();
		
		robot5=new Robot(5,"Robot E", this.getByDescription("Product Entry"), listaWorkstation);
		this.getByDescription("Product Entry").setInside(robot5);
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
	public List<Robot> getRobots(){
		List<Robot> copia = listaRobot;
		return copia;
	}
	
	public List<Workstation>  getWorkstations(){
		List<Workstation> copia = listaWorkstation;
		return copia;
	}
	

}
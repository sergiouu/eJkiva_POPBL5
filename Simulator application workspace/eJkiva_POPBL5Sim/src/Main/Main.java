package Main;

import Packages.Supervisor;
import Robot.Robot;
import Workstation.Workstation;

public class Main {
	
	Robot r1, r2, r3, r4, r5;
	Workstation ws1, ws2, ws3, ws4, ws5, ws6;
	Supervisor s;
	
	public Main() {
		
	}
	
	public void createWorkStations() {
		ws1 = new Workstation(1, "WS1");
		ws2 = new Workstation(2, "WS2");
		ws3 = new Workstation(3, "WS3");
		ws4 = new Workstation(4, "WS4");
		ws5 = new Workstation(5, "WS5");
		ws6 = new Workstation(6, "WS6");
	}
	
	public void createRobots() {
		r1 = new Robot(1, "Robot 1", false, ws1);
		r2 = new Robot(2, "Robot 2", false, ws2);
		r3 = new Robot(3, "Robot 3", false, ws3);
		r4 = new Robot(4, "Robot 4", false, ws4);
		r5 = new Robot(5, "Robot 5", false, ws5);
	}
	
	public void initializeSimulation() {
		createRobots();
	}
	
	public void executeSimulation() throws InterruptedException{
		r1.start();
		/*r2.start();
		r3.start();
		r4.start();
		r5.start();*/
		
		try {
			Thread.sleep(2000);
			System.out.println(r1.getState()+" "+r1.getNamee());
		/*	System.out.println(r2.getState()+" "+r2.getNamee());
			System.out.println(r3.getState()+" "+r3.getNamee());
			System.out.println(r4.getState()+" "+r4.getNamee());
			System.out.println(r5.getState()+" "+r5.getNamee());*/
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	//	s.start();
	}

	public static void main(String[] args) {

		Main simulator = new Main();
		
		simulator.initializeSimulation();
		try {
			simulator.executeSimulation();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

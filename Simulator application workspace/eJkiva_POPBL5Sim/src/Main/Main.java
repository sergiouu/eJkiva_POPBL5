package Main;

import Robot.Robot;

public class Main {
	
	Robot r1, r2, r3, r4, r5;
	
	public Main() {
	}
	
	public void createRobots() {
		r1 = new Robot(1, "Robot 1", false);
		r2 = new Robot(2, "Robot 2", false);
		r3 = new Robot(3, "Robot 3", false);
		r4 = new Robot(4, "Robot 4", false);
		r5 = new Robot(5, "Robot 5", false);
	}
	
	public void initializeSimulation() {
		createRobots();
	}
	
	public void executeSimulation() throws InterruptedException{
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		r5.start();
		
		try {
			Thread.sleep(2000);
			System.out.println(r1.getState());
			System.out.println(r2.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

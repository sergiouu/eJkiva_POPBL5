package Packages;

import java.util.ArrayList;

import Robot.Robot;
import Robot.Workstation;
import SQLConnector.SQLConnector;

public class Supervisor extends Thread{

	ArrayList<Packages> queuedPackage;
	ArrayList<Robot> robotList;
	ArrayList<Workstation> workstations;
	
	//SQLConnector connection;
	
	public Supervisor(ArrayList<Workstation> workstations) {
		this.workstations = new ArrayList<Workstation>();
		this.workstations = workstations;
		queuedPackage = new ArrayList<Packages>();
		robotList = new ArrayList<Robot>();
	}
	
	public void addRobot(Robot r) {
		robotList.add(r);
	}
	
	/*public void addPackageToQueue() {
		ArrayList<Packages> newPackages = new ArrayList<Packages>();
		
	}
	
	public void removeQueuedPackages(ArrayList<Integer> id) {
		for (int i = 0; i < id.size(); i++) {
			for(int j = 0; j < queuedPackage.size(); j++) {
				if(queuedPackage.get(j).id == id.get(i).intValue()) {
					queuedPackage.remove(queuedPackage.get(j));
					break;
				}
			}
		}
	}*/
	
	@Override
	public void run() {
		
	}

}

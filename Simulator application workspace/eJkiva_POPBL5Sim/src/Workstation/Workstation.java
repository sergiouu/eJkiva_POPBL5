package Workstation;

import java.util.ArrayList;

import Packages.Packages;
import Robot.Robot;

public class Workstation {

	int id;
	String name;

	ArrayList<Robot> robotsIn, accessRobots;	
	Packages packageRobot1, packageRobot2, packageRobot3, packageRobot4, packageRobot5;

	public Workstation(int id, String name) {
		this.id = id;
		this.name = name;

		robotsIn = new ArrayList<Robot>();
		accessRobots = new ArrayList<Robot>();

		packageRobot1 = null;
		packageRobot2 = null;
		packageRobot3 = null;
		packageRobot4 = null;
		packageRobot5 = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Robot> getRobotsIn() {
		return robotsIn;
	}

	public void setRobotsIn(ArrayList<Robot> robotsIn) {
		this.robotsIn = robotsIn;
	}

	public ArrayList<Robot> getAccessRobots() {
		return accessRobots;
	}

	public void setAccessRobots(ArrayList<Robot> accessRobots) {
		this.accessRobots = accessRobots;
	}

	public Packages getPackages(int id) {

		Packages packages = new Packages();

		switch(id){
		case 1:
			packages =  packageRobot1;
			break;
		case 2:
			packages =  packageRobot2;
			break;
		case 3:
			packages =  packageRobot3;
			break;
		case 4:
			packages =  packageRobot4;
			break;
		case 5:
			packages =  packageRobot5;
			break;
		}

		return packages;
	}

	public void addPackage(Packages p) {

		switch(p.getRobotDesignated().getRobotId()) {
		case 1:
			packageRobot1 = p;
			break;
		case 2:
			packageRobot2 = p;
			break;
		case 3:
			packageRobot3 = p;
			break;
		case 4:
			packageRobot4 = p;
			break;
		case 5:
			packageRobot5 = p;
			break;
		}

		if(!p.getRobotDesignated().isOn()){
			System.out.println("A ROBOT HAS A PACKAGE WAITING IN WORKSTATION: "+p.getRobotDesignated().getNamee());
			p.getRobotDesignated().wakeUpRobot();
		}
	}

	public void addRobotToWorkstation(Robot r) {
		this.robotsIn.add(r);

		if(robotsIn.size() == 1) {
			ArrayList<Robot> in = new ArrayList<Robot>();
			in = robotsIn;

			for (int i = 0; i < in.size(); i++) {
				if(!in.get(i).isOn()) {
					in.get(i).wakeUpRobot();
				}
			}
		}

	}
	
	public boolean askForAccess(Robot r) {
		if(robotsIn.size() < 1) {
			robotsIn.add(r);
			return true;
		}else {
			return false;
		}
	}
}


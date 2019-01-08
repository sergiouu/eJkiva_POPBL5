package Workstation;

import java.awt.Robot;
import java.util.ArrayList;


public class Workstation {

	int id;
	String name;

	ArrayList<Robot> robotsIn, accessRobots;	
	ArrayList<Packages> packageRobot1, packageRobot2, packageRobot3, packageRobot4, packageRobot5;

	public Workstation(int id, String name) {
		this.id = id;
		this.name = name;

		robotsIn = new ArrayList<Robot>();
		accessRobots = new ArrayList<Robot>();

		packageRobot1 = new ArrayList<Packages>();
		packageRobot2 = new ArrayList<Packages>();
		packageRobot3 = new ArrayList<Packages>();
		packageRobot4 = new ArrayList<Packages>();
		packageRobot5 = new ArrayList<Packages>();
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

	public ArrayList<Packages> getPackageRobot1() {
		return packageRobot1;
	}

	public void setPackageRobot1(ArrayList<Packages> packageRobot1) {
		this.packageRobot1 = packageRobot1;
	}

	public ArrayList<Packages> getPackageRobot2() {
		return packageRobot2;
	}

	public void setPackageRobot2(ArrayList<Packages> packageRobot2) {
		this.packageRobot2 = packageRobot2;
	}

	public ArrayList<Packages> getPackageRobot3() {
		return packageRobot3;
	}

	public void setPackageRobot3(ArrayList<Packages> packageRobot3) {
		this.packageRobot3 = packageRobot3;
	}

	public ArrayList<Packages> getPackageRobot4() {
		return packageRobot4;
	}

	public void setPackageRobot4(ArrayList<Packages> packageRobot4) {
		this.packageRobot4 = packageRobot4;
	}

	public ArrayList<Packages> getPackageRobot5() {
		return packageRobot5;
	}

	public void setPackageRobot5(ArrayList<Packages> packageRobot5) {
		this.packageRobot5 = packageRobot5;
	}

	public ArrayList<Packages> getPackages(int id) {

		ArrayList<Packages> packages = new ArrayList<Packages>();

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
			packageRobot1.add(p);
			break;
		case 2:
			packageRobot2.add(p);
			break;
		case 3:
			packageRobot3.add(p);
			break;
		case 4:
			packageRobot4.add(p);
			break;
		case 5:
			packageRobot5.add(p);
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


package Packages;

import Robot.Robot;
import Workstation.Workstation;

public class Packages {

	int id;
	Robot robotDesignated;
	Workstation wsOrigin, wsDestiny;
	

	public Packages() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Robot getRobotDesignated() {
		return robotDesignated;
	}

	public void setRobotDesignated(Robot robotDesignated) {
		this.robotDesignated = robotDesignated;
	}

	public Workstation getWsOrigin() {
		return wsOrigin;
	}

	public void setWsOrigin(Workstation wsOrigin) {
		this.wsOrigin = wsOrigin;
	}

	public Workstation getWsDestiny() {
		return wsDestiny;
	}

	public void setWsDestiny(Workstation wsDestiny) {
		this.wsDestiny = wsDestiny;
	}

}

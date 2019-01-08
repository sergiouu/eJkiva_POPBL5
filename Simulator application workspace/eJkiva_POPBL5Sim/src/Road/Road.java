package Road;

import java.util.ArrayList;

import Robot.Robot;
import Robot.Workstation;

public class Road {

	int id;
	Workstation wsEntrance, wsExit;
	Robot robotOnRoad;

	ArrayList<Robot> robotWating;

	public Road(int id, Workstation wsEntrance, Workstation wsExit) {
		this.id = id;
		this.wsEntrance = wsEntrance;
		this.wsExit = wsExit;

		this.robotWating = new ArrayList<Robot>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Workstation getWsEntrance() {
		return wsEntrance;
	}

	public void setWsEntrance(Workstation wsEntrance) {
		this.wsEntrance = wsEntrance;
	}

	public Workstation getWsExit() {
		return wsExit;
	}

	public void setWsExit(Workstation wsExit) {
		this.wsExit = wsExit;
	}

	public Robot getRobotOnRoad() {
		return robotOnRoad;
	}

	public void setRobotOnRoad(Robot robotOnRoad) {
		this.robotOnRoad = robotOnRoad;
	}

	public ArrayList<Robot> getRobotWating() {
		return robotWating;
	}

	public void setRobotWating(ArrayList<Robot> robotWating) {
		this.robotWating = robotWating;
	}

	public void addRobotWaiting(Robot r) {
		robotWating.add(r);
	}

	public void askForPermission(Robot r) {
		if(onRoad()) {
			addRobotWaiting(r);
			
			r.waitAccess();
		}else {
			setRobotOnRoad(r);
		}
	}

	public void notifyAccessToRoad() {
		Robot r = robotWating.get(0);
		r.notifyWaitingAccessRobot();
		robotWating.remove(0);
	}
	
	private boolean onRoad() {
		if(robotOnRoad == null) {
			return false;
		}else {
			return true;
		}
	}
}

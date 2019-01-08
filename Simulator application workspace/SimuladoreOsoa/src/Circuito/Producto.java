package Circuito;

import Objeto.Robot;

public class Producto {
	int id;
	String description;
	Workstation wsActual, wsDestion;//esto tambien lo tiene el robot uno de los dos esta de sobra
	Robot robot;
	
	public Producto(int id, String description, Workstation ws) {
		this.id=id;
		this.description=description;
		this.wsActual = ws;
		this.robot=null;
		}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public Workstation getWsActual() {
		return wsActual;
	}
	public void setWsActual(Workstation wsActual) {
		this.wsActual = wsActual;
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	
}
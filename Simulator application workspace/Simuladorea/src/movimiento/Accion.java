package movimiento;

import objeto.Robot;

public class Accion {
	final static float MAX_LENGTH=5;
	final static float MIN_LENGTH=0;
	Robot robot;
	
	public Accion(Robot robot) {
		this.robot=robot;
    }
	
	public boolean jeitsi() {
		System.out.println("jeitsi");
		if((robot.getWsActual().getPosX()%2)==0) {
		robot.getWsActual().setPosY(robot.getWsActual().getPosY()-1);
		return true;}
		return false;
	}
	
	public boolean igo() {
		if((robot.getWsActual().getPosX()%2)!=0) {
			System.out.println("Igo");
		robot.getWsActual().setPosY(robot.getWsActual().getPosY()+1);
		return true;}
		return false;
	}
	
	public boolean aurrera() {
		System.out.println("aurrera");
		if(robot.getWsActual().getPosY()==1) {
		    if(robot.getWsActual().getPosX()==MIN_LENGTH) {robot.getWsActual().setPosY(robot.getWsActual().getPosY()-1);}
		    else{robot.getWsActual().setPosX(robot.getWsActual().getPosX()-1);}
		    return true;
		}
		else {
			//if(robot.getPosY()!=0) {
			    if(robot.getWsActual().getPosX()==MAX_LENGTH) {robot.getWsActual().setPosY(robot.getWsActual().getPosY()+1);}
			    else{robot.getWsActual().setPosX(robot.getWsActual().getPosX()+1);}
			    return true;
		//	}
			}
	}
}

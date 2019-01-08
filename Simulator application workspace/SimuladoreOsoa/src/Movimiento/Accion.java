package Movimiento;

import Objeto.Robot;

public class Accion {
	final static float MAX_LENGTH=5;
	final static float MIN_LENGTH=0;
	Robot robot;
	
	public Accion(Robot robot) {
		this.robot=robot;
    }
	
	public boolean jeitsi() {
		System.out.println("jeitsi");
		if((robot.getPosX()%2)==0) {
		robot.setPosY(robot.getPosY()-1);
		return true;}
		return false;
	}
	
	public boolean igo() {
		if((robot.getPosX()%2)!=0) {
			System.out.println("Igo");
		robot.setPosY(robot.getPosY()+1);
		return true;}
		return false;
	}
	
	public boolean aurrera() {
		System.out.println("aurrera");
		if(robot.getPosY()==1) {
		    if(robot.getPosX()==MIN_LENGTH) {robot.setPosY(robot.getPosY()-1);}
		    else{robot.setPosX(robot.getPosX()-1);}
		    return true;
		}
		else {
			//if(robot.getPosY()!=0) {
			    if(robot.getPosX()==MAX_LENGTH) {robot.setPosY(robot.getPosY()+1);}
			    else{robot.setPosX(robot.getPosX()+1);}
			    return true;
		//	}
			}
	}
}

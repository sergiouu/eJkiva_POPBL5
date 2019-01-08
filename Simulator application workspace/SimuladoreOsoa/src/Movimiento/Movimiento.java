package Movimiento;

import Circuito.Circuito;
import Circuito.Workstation;
import Objeto.Robot;

public class Movimiento {

	static int xValue;
	static int yValue;
	int finPosX;
	int finPosY;
	boolean amaiera;
	Robot robot;
	Mover mover;
	Workstation workstationDestino;
	Circuito circuito;

    public Movimiento(int idRobot,int idWorkstation, Circuito circuito) {
    	
    	this.circuito=circuito;
		this.robot=searchRobot(idRobot);
		this.finPosX=finPosX;
		this.finPosY=finPosY;
		this.amaiera=false;
		
		this.workstationDestino=searchWorkstation(idWorkstation);
		
		mover=new Mover(this.robot, this);
    }
    private Robot searchRobot(int idRobot) {
		
		for(Robot robot : circuito.getRobots()) {
			if(robot.getId()==idRobot) {
				return robot;
			}
			
		}
		return null;
	}
    private Workstation searchWorkstation(int idWorkstation) {
		
		for(Workstation workstation : circuito.getWorkstations()) {
			if(workstation.getId()==idWorkstation) {
				return workstation;
			}
			
		}
		return null;
	}    
	public void move() {
		
		do {
			
			//wait
			xValue=robot.getPosX()-workstationDestino.getPosX();
			yValue=robot.getPosY()-workstationDestino.getPosY();
			
			if(xValue>0 && yValue>0) {mover.xPosyPos();}
			else if(xValue>0 && yValue<0) {mover.xPosyNeg();}
			else if(xValue>0 && yValue==0) {mover.xPosyZero();}
			else if(xValue<0 && yValue>0) {mover.xNegyPos();}
			else if(xValue<0 && yValue<0) {mover.xNegyNeg();}
			else if(xValue<0 && yValue==0) {mover.xNegyZero();}
			else if(xValue==0 && yValue>0) {mover.xZeroyPos();}
			else if(xValue==0 && yValue<0) {mover.xZeroyNeg();}
			else if(xValue==0 && yValue==0) {mover.xZeroyZero();}
			
			//notify
		}while(!isAmaiera());	
		}
    public int getxValue() {
		return xValue;
	}

	public int getyValue() {
		return yValue;
	}
	public boolean isAmaiera() {
		return amaiera;
	}
	public void setAmaiera(boolean amaieraa) {
		amaiera = amaieraa;
	}
	

}

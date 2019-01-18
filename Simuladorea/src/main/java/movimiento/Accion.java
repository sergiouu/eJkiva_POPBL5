package movimiento;

import java.util.ArrayList;
import java.util.List;

import circuito.Robot;
import circuito.Workstation;

/**
*
* <h2> Accion Class </h2>
* <p>
* Manages and changes the workstation destination</p>
*
* @author mbenoga 09/Jan/2018
*
*/
public class Accion {
	final static float MAX_LENGTH=5;
	final static float MIN_LENGTH=0;
	Robot robot;
	List<Workstation> workstationList;
	public Accion(Robot robot, List<Workstation> workstationList2) {
		
		this.robot=robot;
		this.workstationList=workstationList2;
    }

	
	/**
*
* <h2> jeitsi </h2>
* <p>
* Checks if can go down. If it is possible calls to cambiarWorkstationActual </p>
*
* @return Boolean
*
* @author mbengoa 20/Dec/2018
*
*/
	public boolean jeitsi() {
		if((robot.getWsActual().getPosX()%2)==0) {
		//robot.setPosY(robot.getWorkstationActual().getPosY()-1);
		cambiarWorkstationActual(false,false);
		return true;}
		return false;
	}
/**
*
* <h2> igo </h2>
* <p>
* Checks if can go up. If it is possible calls to cambiarWorkstationActual </p>
*
* @return Boolean
*
* @author mbengoa 20/Dec/2018
*
*/
	public boolean igo() {
		
		if((robot.getWsActual().getPosX()%2)!=0) {
		//robot.setPosY(robot.getWorkstationActual().getPosY()+1);
		cambiarWorkstationActual(true,false);
		return true;}
		return false;
	}
/**
*
* <h2> aurrera </h2>
* <p>
* Checks if can go ahead. Checks the current Y position and if it is possible calls to cambiarWorkstationActual </p>
*
* @return Boolean
*
* @author mbengoa 20/Dec/2018
*
*/	
	public boolean aurrera() {
		if(robot.getWsActual().getPosY()==1) {
		    if(robot.getWsActual().getPosX()==MIN_LENGTH) {cambiarWorkstationActual(false,false);}//robot.setPosY(robot.getWorkstationActual().getPosY()-1);}
		    else{cambiarWorkstationActual(false,true);}//robot.setPosX(robot.getWorkstationActual().getPosY()-1);}
		    return true;
		}
		else {
			//if(robot.getPosY()!=0) {
			    if(robot.getWsActual().getPosX()==MAX_LENGTH) {cambiarWorkstationActual(true,false);}//robot.setPosY(robot.getWorkstationActual().getPosY()+1);}
			    else{cambiarWorkstationActual(true,true);}
			    return true;
		//	}
			}
	}
	
	//signo    true +      false -
	//position true X      false Y
/**
*
* <h2> cambiarWorkstationActual </h2>
* <p>
* Changes the workstation. Depending on signo(if it is true means that +1 if is false -1) and position (if it is true means X if it is Y means -1
*  </p>
*
* @param signo
* @param position
*
* @author mbengoa 20/Dec/2018
* @modified mbengoa 7/Jan/2019 Filters and finds a Worktation
*
*/	
	private void cambiarWorkstationActual(boolean signo,boolean position) {
		int nextX = -1,nextY = -1;
		
		if     (signo==true && position==true) {nextX=robot.getWsActual().getPosX()+1; nextY=robot.getWsActual().getPosY();}
		else if(signo==true && position==false) {nextX=robot.getWsActual().getPosX(); nextY=robot.getWsActual().getPosY()+1;}
		else if(signo==false && position==true) {nextX=robot.getWsActual().getPosX()-1; nextY=robot.getWsActual().getPosY();}
		else if(signo==false && position==false) {nextX=robot.getWsActual().getPosX(); nextY=robot.getWsActual().getPosY()-1;}
		
		for(Workstation w : workstationList) {
			if(w.getPosX()==nextX && w.getPosY()==nextY) {
				robot.setWsSiguiente(w);
			}
		}
		
	}
}

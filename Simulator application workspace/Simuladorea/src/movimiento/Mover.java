package movimiento;

import java.util.List;

import circuito.Robot;
import circuito.Workstation;

/**
*
* <h2> Mover Class </h2>
* <p>
* Manages and moves the action of the robot </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
public class Mover {
	
	Robot robot;
    
	Accion accion;
	
/**
*
* <h2> Mover </h2>
* <p>
* Constructor of Mover </p>
*
* @param robot
* @param workstationList
*
* @author mbengoa 20/Dec/2018
*
*/
	public Mover(Robot robot,List<Workstation> workstationList) {
		
		this.robot=robot;
		accion=new Accion(this.robot,workstationList);

    }

/**
*
* <h2> xPosyPos </h2>
* <p>
* Checks if can go ahead. If is not possible goes down </p>
*
* @author mbengoa 20/Dec/2018
*
*/		
	public void xPosyPos() {
		if(accion.aurrera()) {}
		else accion.jeitsi();
	}
/**
*
* <h2> xPosyNeg </h2>
* <p>
* Checks if can go up. If is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xPosyNeg() {
		if(accion.igo()) {}
		else accion.aurrera();
	}
/**
*
* <h2> xPosyZero </h2>
* <p>
* Checks if can go up. If is not checks if it can go up. If it is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xPosyZero() {
		if(robot.getWsActual().getPosY()==1) {accion.aurrera();}
		else {
			if(accion.igo()) {}
			else accion.aurrera();}	
	}
/**
*
* <h2> xNegyPos </h2>
* <p>
* Checks if can go down. If is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xNegyPos() {
		//Beti goian dago.Normal bada Aurrera eta begiratu eskina den. Hala bada igo edo jeitsi
		if(accion.jeitsi()) {}
		else accion.aurrera();
	}
/**
*
* <h2> xNegyNeg </h2>
* <p>
* Checks if can go ahead. If is not possible goes up </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xNegyNeg() {
		if(accion.aurrera()) {}
		else accion.igo();
	}
/**
*
* <h2> xNegyZero </h2>
* <p>
* Checks if can go ahead. If is not checks if it can go down. If it is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xNegyZero() {
		if(robot.getWsActual().getPosY()==0) {accion.aurrera();}
		else {
			if(accion.jeitsi()) {}
			else accion.aurrera();}
	}
/**
*
* <h2> xZeroyPos </h2>
* <p>
* Checks if can go down. If is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void xZeroyPos() {
		
		if(accion.jeitsi()) {}
		else accion.aurrera();
	}
/**
*
* <h2> xZeroyNeg </h2>
* <p>
* Checks if can go up. If is not possible goes ahead </p>
*
* @author mbengoa 20/Dec/2018
*
*/		
	public void xZeroyNeg() {
		if(accion.igo()) {}
		else accion.aurrera();
	}	

}


package movimiento;

import java.util.ArrayList;
import java.util.List;

import circuito.Workstation;
import objeto.Robot;

public class Mover {
	
	Robot robot;
    
	Accion accion;
	
	public Mover(Robot robot,List<Workstation> workstationList) {
		
		this.robot=robot;
		accion=new Accion(this.robot,workstationList);

    }
	
	public void xPosyPos() {
		if(accion.aurrera()) {}
		else accion.jeitsi();
		
		System.out.println(robot);
	}

	public void xPosyNeg() {
		if(accion.igo()) {}
		else accion.aurrera();
		
		System.out.println(robot);
		
	}

	public void xPosyZero() {
		if(robot.getWsActual().getPosY()==1) {accion.aurrera();}
		else {
			if(accion.igo()) {}
			else accion.aurrera();}
		
		System.out.println(robot);
		
	}

	public void xNegyPos() {
		//Beti goian dago.Normal bada Aurrera eta begiratu eskina den. Hala bada igo edo jeitsi
		if(accion.jeitsi()) {}
		else accion.aurrera();
		
		System.out.println(robot);
		
	}

	public void xNegyNeg() {
		if(accion.aurrera()) {}
		else accion.igo();
		
		System.out.println(robot);
	}

	public void xNegyZero() {
		if(robot.getWsActual().getPosY()==0) {accion.aurrera();}
		else {
			if(accion.jeitsi()) {}
			else accion.aurrera();}
		System.out.println();
		
	}

	public void xZeroyPos() {
		if(accion.jeitsi()) {}
		else accion.aurrera();
		
		System.out.println(robot);
		
	}
	
	public void xZeroyNeg() {
		if(accion.igo()) {}
		else accion.aurrera();
		System.out.println(robot);
		
	}

	public void xZeroyZero() {
		System.out.println(robot);
	}

}

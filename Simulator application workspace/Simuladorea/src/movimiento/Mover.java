package movimiento;

import objeto.Robot;

public class Mover {
	
	Robot robot;
    Movimiento movimiento;
	Accion accion;
	
	public Mover(Robot robot, Movimiento movimiento) {
		
		this.robot=robot;
		this.movimiento=movimiento;
		accion=new Accion(this.robot);

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
		if(robot.getWsActual().getPosX()==0) {accion.aurrera();}
		else {
			if(accion.jeitsi()) {}
			else accion.aurrera();}
		System.out.println(robot);
		
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
		
		movimiento.setAmaiera(true);
		System.out.println(robot);
		
	}

}

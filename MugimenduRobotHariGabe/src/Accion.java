import java.util.ArrayList;

public class Accion {
	final static float MAX_LENGTH=5;
	final static float MIN_LENGTH=0;
	Robot robot;
	ArrayList<Workstation> workstationList;
	public Accion(Robot robot, ArrayList<Workstation> workstationList) {
		
		this.robot=robot;
		this.workstationList=workstationList;
    }

	
	public boolean jeitsi() {
		System.out.println("jeitsi");
		if((robot.getWorkstationActual().getPosX()%2)==0) {
		//robot.setPosY(robot.getWorkstationActual().getPosY()-1);
		robot.setWorkstationActual(cambiarWorkstationActual(false,false));
		return true;}
		return false;
	}

	public boolean igo() {
		if((robot.getWorkstationActual().getPosX()%2)!=0) {
			System.out.println("Igo");
		//robot.setPosY(robot.getWorkstationActual().getPosY()+1);
		robot.setWorkstationActual(cambiarWorkstationActual(true,false));
		return true;}
		return false;
	}
	public boolean aurrera() {
		System.out.println("aurrera");
		if(robot.getWorkstationActual().getPosY()==1) {
		    if(robot.getWorkstationActual().getPosX()==MIN_LENGTH) {robot.setWorkstationActual(cambiarWorkstationActual(false,false));}//robot.setPosY(robot.getWorkstationActual().getPosY()-1);}
		    else{robot.setWorkstationActual(cambiarWorkstationActual(false,true));}//robot.setPosX(robot.getWorkstationActual().getPosY()-1);}
		    return true;
		}
		else {
			//if(robot.getPosY()!=0) {
			    if(robot.getWorkstationActual().getPosX()==MAX_LENGTH) {robot.setWorkstationActual(cambiarWorkstationActual(true,false));}//robot.setPosY(robot.getWorkstationActual().getPosY()+1);}
			    else{robot.setWorkstationActual(cambiarWorkstationActual(true,true));}//robot.setPosX(robot.getWorkstationActual().getPosX()+1);}
			    return true;
		//	}
			}
	}
	
	//signo    true +      false -
	//position true X      false Y

	private Workstation cambiarWorkstationActual(boolean signo,boolean position) {
		int nextX = -1,nextY = -1;
		
		if     (signo==true && position==true) {nextX=robot.getWorkstationActual().getPosX()+1; nextY=robot.getWorkstationActual().getPosY();}
		else if(signo==true && position==false) {nextX=robot.getWorkstationActual().getPosX(); nextY=robot.getWorkstationActual().getPosY()+1;}
		else if(signo==false && position==true) {nextX=robot.getWorkstationActual().getPosX()-1; nextY=robot.getWorkstationActual().getPosY();}
		else if(signo==false && position==false) {nextX=robot.getWorkstationActual().getPosX(); nextY=robot.getWorkstationActual().getPosY()-1;}
		
		for(Workstation w : workstationList) {
			if(w.getPosX()==nextX && w.getPosY()==nextY) {
				return w;
			}
		}
		return null;
	}
}

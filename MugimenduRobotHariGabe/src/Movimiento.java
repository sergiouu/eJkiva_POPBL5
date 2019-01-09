import java.util.ArrayList;

public class Movimiento {

	static int xValue;
	static int yValue;
	int finPosX;
	int finPosY;
	boolean amaiera;
	Robot robot;
	Mover mover;
	

    public Movimiento(Robot robot, ArrayList<Workstation> workstationList) {
		this.robot=robot;
		
		this.finPosX=robot.getWorkstationDestino().getPosX();
		this.finPosY=robot.getWorkstationDestino().getPosY();
		this.amaiera=false;
		
		
		mover=new Mover(this.robot, this,workstationList);
    }
    public void move() {
		//elena hau da threadendako posizinua
    	//cada vez que se mueve if ian sartzen da!
		do {
			xValue=robot.getWorkstationActual().getPosX()-finPosX;
			yValue=robot.getWorkstationActual().getPosY()-finPosY;
			
			if(xValue>0 && yValue>0) {mover.xPosyPos();}
			else if(xValue>0 && yValue<0) {mover.xPosyNeg();}
			else if(xValue>0 && yValue==0) {mover.xPosyZero();}
			else if(xValue<0 && yValue>0) {mover.xNegyPos();}
			else if(xValue<0 && yValue<0) {mover.xNegyNeg();}
			else if(xValue<0 && yValue==0) {mover.xNegyZero();}
			else if(xValue==0 && yValue>0) {mover.xZeroyPos();}
			else if(xValue==0 && yValue<0) {mover.xZeroyNeg();}
			else if(xValue==0 && yValue==0) {mover.xZeroyZero();}
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


public class Movimiento {

	static int xValue;
	static int yValue;
	int finPosX;
	int finPosY;
	boolean amaiera;
	Robot robot;
	Mover mover;

    public Movimiento(Robot robot, int finPosX,int finPosY) {
		this.robot=robot;
		this.finPosX=finPosX;
		this.finPosY=finPosY;
		this.amaiera=false;
		
		mover=new Mover(this.robot, this);
    }
    public void move() {
		
		do {
			xValue=robot.getPosX()-finPosX;
			yValue=robot.getPosY()-finPosY;
			
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

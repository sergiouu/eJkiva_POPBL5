
public class Robot {
	int posX;
	int posY;
	boolean amaiera;

	public Robot(int posX,int posY){
		this.posX = posX;
		this.posY=posY;
		amaiera=false;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	@Override
	public String toString() {
		return "PosX: "+ this.posX +"\n" +
		        "PosY: " + this.posY +"\n";
	}	
}
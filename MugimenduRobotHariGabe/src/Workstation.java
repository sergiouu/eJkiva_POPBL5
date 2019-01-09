public class Workstation {
	int posX;
	int posY;
	Workstation workstationActual,workstationDestino;
	String description;
	public Workstation(int posX,int posY){
		this.posX = posX;
		this.posY=posY;
		this.description=null;
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
	
}
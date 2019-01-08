package Circuito;
import java.util.ArrayList;

public class Workstation {
	int id, posX, posY;
	boolean state;
	String description;
	ArrayList<Producto> listaProductos;
	
	public Workstation(int id,int posX,int posY, String description) {
		this.id=id;
		this.posX=posX;
		this.posY=posY;
		this.state=false;
		this.description=description;
		listaProductos = new ArrayList<Producto>();	
	}
	
	public void añadirProducto(Producto p) {
		listaProductos.add(p);
	}
	public int getId() {
		return id;
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
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}

	

}

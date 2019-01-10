package circuito;
import java.util.ArrayList;

import producto.Producto;

public class Workstation {
	int id, posX, posY;
	boolean stateInside, stateOutside;
	String description;
	ArrayList<Producto> listaProductos;
	
	public Workstation(int id,int posX,int posY, String description) {
		this.id=id;
		this.posX=posX;
		this.posY=posY;
		this.stateInside=false;
		this.stateOutside=false;
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
	public boolean isStateInside() {
		return stateInside;
	}
	public void setState(boolean state) {
		this.stateInside = state;
	}
	public boolean isStateOutside() {
		return stateInside;
	}
	public void setOutside(boolean state) {
		this.stateInside = state;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@Override
	public String toString() {
		return id+"."+description;
	}
	

}

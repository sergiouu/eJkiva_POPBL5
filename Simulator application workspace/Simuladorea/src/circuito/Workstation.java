package circuito;
import java.util.ArrayList;
import java.util.List;

import SQLConnector.SQLConnector;
import producto.Pedido;
import producto.Producto;

public class Workstation {
	int id, posX, posY;
	Robot inside, outside, insideEspera, outsideEspera;
	String description;
	
	List<Producto> listaProductos;
	
	SQLConnector conexion;
	
	public Workstation(int id,int posX,int posY, String description) {
		this.id=id;
		this.posX=posX;
		this.posY=posY;
		this.inside = null;
		this.outside = null;
		this.description=description;
		listaProductos = new ArrayList<Producto>();	
		
	}

	
	public Robot getInsideEspera() {
		return insideEspera;
	}
	
	public void setInsideEspera(Robot insideEspera) {
		this.insideEspera = insideEspera;
	}

	public Robot getOutsideEspera() {
		return outsideEspera;
	}

	public void setOutsideEspera(Robot outsideEspera) {
		this.outsideEspera = outsideEspera;
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
		if (this.inside == null) return false;
		return true;
	}
	
	public boolean isStateOutside() {
		if (this.outside == null) return false;
		return true;
	}

	public void setOutside(Robot outside) {
		this.outside = outside;
	}

	public Robot getInside() {
		return this.inside;
	}

	public Robot getOutside() {
		return this.outside;
	}
	public void setInside(Robot inside) {		
		conexion = new SQLConnector(null);
		if(inside==null) {
			conexion.actualizarWorkstation(0, this.getId());
		}
		else{
			conexion.actualizarWorkstation(inside.getId(), this.getId());
		}
		this.inside = inside;
	}

	
	public String getDescription() {
		return description;
	}
	
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@Override
	public String toString() {
		return id+"."+description + listaProductos.size();
	}
	

}

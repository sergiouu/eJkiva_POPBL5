package circuito;
import java.util.ArrayList;
import java.util.List;

import producto.Producto;

/**
*
* <h2> Workstation Class </h2>
* <p>
* Manages all the information about the Workstation</p>
*
* @author mbenoga 09/Jan/2018
*
*/	
public class Workstation {
	int id, posX, posY;
	Robot inside, outside, insideEspera, outsideEspera;
	String description;
	
	List<Producto> listaProductos;

	/**
*
* <h2> Workstation </h2>
* <p>
* Constructor of Workstation </p>
*
* @param id
* @param posX
* @param posY
* @param description
*
* @author mbengoa 20/Dec/2018
*
*/
	public Workstation(int id,int posX,int posY, String description) {
		this.id=id;
		this.posX=posX;
		this.posY=posY;
		this.inside = null;
		this.outside = null;
		this.description=description;
		listaProductos = new ArrayList<Producto>();	
	}
	
/**
*
* <h2> isPedidoCompleto </h2>
* <p>
* Checks if the order is complete. If is true returns true </p>
*
* @param size
* @param id
*
* @return boolean
*
* @author mbengoa 20/Dec/2018
*
*/	
	public boolean isPedidoCompleto(int size, int id) {
		int kont = 0;
		for(Producto p: listaProductos) {
			if(p.getIdPedido() == id) {
				kont++;
			}
		}
		if(kont == size) {
			return true;
		}
		else return false;
	}
/**
*
* <h2> getInsideEspera </h2>
* <p>
* Getter for InsideEspera </p>
*
* @return Robot
*
* @author mbengoa 20/Dec/2018
*
*/	
	public Robot getInsideEspera() {
		return insideEspera;
	}
/**
*
* <h2> setInsideEspera </h2>
* <p>
* Setter for InsideEspera </p>
*
* @param insideEspera
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void setInsideEspera(Robot insideEspera) {
		this.insideEspera = insideEspera;
	}
/**
*
* <h2> getOutsideEspera </h2>
* <p>
* Getter for outsideEspera </p>
*
* @return outsideEspera
*
* @author mbengoa 20/Dec/2018
*
*/
	public Robot getOutsideEspera() {
		return outsideEspera;
	}
/**
*
* <h2> setOutsideEspera </h2>
* <p>
* Setter for InsideEspera </p>
*
* @param outsideEspera
*
* @author mbengoa 20/Dec/2018
*
*/
	public void setOutsideEspera(Robot outsideEspera) {
		this.outsideEspera = outsideEspera;
	}
/**
*
* <h2> añadirProducto </h2>
* <p>
* Adds a product to listaProductos</p>
*
* @param p
*
* @author mbengoa 20/Dec/2018
*
*/
	public void añadirProducto(Producto p) {
		listaProductos.add(p);
	}
/**
*
* <h2> getId </h2>
* <p>
* Getter for outsideEspera </p>
*
* @return Int
*
* @author mbengoa 20/Dec/2018
*
*/	
	public int getId() {
		return id;
	}
/**
*
* <h2> getPosX </h2>
* <p>
* Getter for posX </p>
*
* @return Int
*
* @author mbengoa 20/Dec/2018
*
*/
	public int getPosX() {
		return posX;
	}
/**
*
* <h2> setPosX </h2>
* <p>
* setter for setPosX </p>
*
* @param posX
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void setPosX(int posX) {
		this.posX = posX;
	}
/**
*
* <h2> getPosY </h2>
* <p>
* getter for posY </p>
*
* @return Int
*
* @author mbengoa 20/Dec/2018
*
*/
	public int getPosY() {
		return posY;
	}
/**
*
* <h2> setPosY </h2>
* <p>
* Setter for setPosY </p>
*
* @param posY
*
* @author mbengoa 20/Dec/2018
*
*/
	public void setPosY(int posY) {
		this.posY = posY;
	}
/**
*
* <h2> isStateInside </h2>
* <p>
* getter for StateInside </p>
*
* @return boolean
*
* @author mbengoa 20/Dec/2018
*
*/
	public boolean isStateInside() {
		if (this.inside == null) return false;
		return true;
	}
/**
*
* <h2> setInside </h2>
* <p>
* Setter for Inside </p>
*
* @param inside
*
* @author mbengoa 20/Dec/2018
*
*/	
	public void setInside(Robot inside) {
		this.inside = inside;
	}
/**
*
* <h2> isStateOutside </h2>
* <p>
* Getter for StateOutside </p>
*
* @return boolean
*
* @author mbengoa 20/Dec/2018
*
*/
	public boolean isStateOutside() {
		if (this.outside == null) return false;
		return true;
	}
/**
*
* <h2> setOutside </h2>
* <p>
* Setter for Outside </p>
*
* @param outside
*
* @author mbengoa 20/Dec/2018
*
*/
	public void setOutside(Robot outside) {
		this.outside = outside;
	}
/**
*
* <h2> getInside </h2>
* <p>
* Getter for Inside </p>
*
* @return Robot
*
* @author mbengoa 20/Dec/2018
*
*/
	public Robot getInside() {
		return this.inside;
	}
/**
*
* <h2> getOutside </h2>
* <p>
* Getter for Outside </p>
*
* @return Robot
*
* @author mbengoa 20/Dec/2018
*
*/
	public Robot getOutside() {
		return this.outside;
	}
/**
*
* <h2> getDescription </h2>
* <p>
* Getter for description </p>
*
* @return String
*
* @author mbengoa 20/Dec/2018
*
*/	
	public String getDescription() {
		return description;
	}
/**
*
* <h2> getListaProductos </h2>
* <p>
* getter for ListaProductos</p>
*
* @return List<Producto>
*
* @author mbengoa 20/Dec/2018
*
*/	
	public List<Producto> getListaProductos() {
		return listaProductos;
	}
/**
*
* <h2> setListaProductos </h2>
* <p>
* Setter for ListaProductos </p>
*
* @param listaProductos
*
* @author mbengoa 20/Dec/2018
*
*/
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
/**
*
* <h2> toString </h2>
* <p>
* To string </p>
*
* @param posY
* @param description
*
* @return String
*
* @Override toString
*
* @author mbengoa 20/Dec/2018
*
*/
	@Override
	public String toString() {
		return id+"."+description + listaProductos.size();
	}
	

}

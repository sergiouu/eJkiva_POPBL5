package producto;

import java.util.Date;
import java.util.List;
/**
*
* <h2> Pedido Class </h2>
* <p>
* Orders class</p>
*
* @author mbengoa 20/Dec/2018
*
*/
public class Pedido {
	
	int id, cantidadProductos;
	List<Producto> listaProductos;
	Date fechaSalida;
	
	/**
	*
	* <h2> Pedido </h2>
	* <p>
	* Constructor of pedido</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Pedido() {
		
	}
	
	/**
	*
	* <h2> toString </h2>
	* <p>
	* To string of Pedido </p>
	*
	* @author elarretegui 09/Jan/2019
	*
	*/
	@Override
	public String toString() {
		return "pedido-id:"+id;
	}
	/**
	*
	* <h2> getFechaSalida </h2>
	* <p>
	* Get fecha salida </p>
	*
	*@return Date
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Date getFechaSalida() {
		return fechaSalida;
	}
	/**
	*
	* <h2> setFechaSalida </h2>
	* <p>
	* Set fecha salida </p>
	*
	*@param fechaSalida
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	/**
	*
	* <h2> añadirProducto </h2>
	* <p>
	* Add product to the list of orders </p>
	*
	*@return Date
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void añadirProducto(Producto p) {
		listaProductos.add(p);
	}
	/**
	*
	* <h2> getId </h2>
	* <p>
	* Get id of order </p>
	*
	*@param int
	*
	* @author elarretegui 09/Jan/2019
	*/
	public int getId() {
		return id;
	}
	/**
	*
	* <h2> setId </h2>
	* <p>
	* Set id of order </p>
	*
	*@param int
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setId(int id) {
		this.id = id;
	}
	/**
	*
	* <h2> getCantidad </h2>
	* <p>
	* Get quantity of products in order </p>
	*
	*@param id
	*
	* @author elarretegui 09/Jan/2019
	*/
	public int getCantidad() {
		return cantidadProductos;
	}
	/**
	*
	* <h2> getProduct </h2>
	* <p>
	* Get the list of products in the order </p>
	*
	*@param List<Producto>
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public List<Producto> getProducto() {
		return listaProductos;
	}
	/**
	*
	* <h2> setCantidad </h2>
	* <p>
	* Set quantity of products in order </p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setCantidad(int cantidad) {
		this.cantidadProductos = cantidad;
	}

}

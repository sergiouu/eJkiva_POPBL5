package producto;

import circuito.Workstation;
/**
*
* <h2> Pedido Class </h2>
* <p>
* Products clAA</p>
*
* @author mbengoa 20/Dec/2018
*
*/
public class Producto {
	int id, idPedido, workstationID, cantidad;
	String description;
	Workstation wsActual, wsDestino;
	
	/**
	*
	* <h2> Producto </h2>
	* <p>
	* Constructor of Product</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Producto() {
	
	}
	/**
	*
	* <h2> getCantidad </h2>
	* <p>
	* Get quantity of this type of product</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/
	public int getCantidad() {
		return cantidad;
	}

	/**
	*
	* <h2> setCantidad </h2>
	* <p>
	* Get quantity of this type of product</p>
	*
	*@param cantidad
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	*
	* <h2> getId </h2>
	* <p>
	* Get ID of product</p>
	*
	*@return int
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
	* Set ID of product</p>
	*
	*@param id
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	*
	* <h2> getWorkstationID </h2>
	* <p>
	* Get ID of workstation to which it corresponds</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public int getWorkstationID() {
		return workstationID;
	}
	/**
	*
	* <h2> setWorkstationID </h2>
	* <p>
	* Set ID of workstation</p>
	*
	*@param workstationID
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setWorkstationID(int workstationID) {
		this.workstationID = workstationID;
	}
	/**
	*
	* <h2> getIdPedido </h2>
	* <p>
	* Get ID of product</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/

	public int getIdPedido() {
		return idPedido;
	}
	/**
	*
	* <h2> setIdPedido </h2>
	* <p>
	* Set ID of  order</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/

	public void setIdPedido(int id) {
		this.idPedido = id;
	}
	/**
	*
	* <h2> getDescription </h2>
	* <p>
	* Get description of the product</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/

	public String getDescription() {
		return description;
	}
	/**
	*
	* <h2> getDescription </h2>
	* <p>
	* Get description of the product</p>
	*
	*@param id
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}
	/**
	*
	* <h2> getWsActual </h2>
	* <p>
	*  Get the actual workstation in which is the product</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public Workstation getWsActual() {
		return wsActual;
	}
	
	/**
	*
	* <h2> setWsActual </h2>
	* <p>
	* Set the actual workstation in which is the product</p>
	*
	*@param wsActual
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public void setWsActual(Workstation wsActual) {
		this.wsActual = wsActual;
	}
	
	/**
	*
	* <h2> getWsDestino </h2>
	* <p>
	* Get the workstation to which the product must be moved</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public Workstation getWsDestino() {
		return wsDestino;
	}
	
	/**
	*
	* <h2> setWsDestino </h2>
	* <p>
	* Get the workstation to which the product must be moved</p>
	*
	*@param wsDestino
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setWsDestino(Workstation wsDestino) {
		this.wsDestino = wsDestino;
	}

	/**
	*
	* <h2> toString </h2>
	* <p>
	* To string of Product class</p>
	*
	*@return String
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	@Override
	public String toString() {
		return "idProducto:" + id + wsActual + wsDestino;
	}

	
	
}
package producto;

import circuito.Workstation;

public class Producto {
	int id, idPedido, workstationID, cantidad;
	String description;
	Workstation wsActual, wsDestino;
	
	public Producto() {
	
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getWorkstationID() {
		return workstationID;
	}

	public void setWorkstationID(int workstationID) {
		this.workstationID = workstationID;
	}

	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int id) {
		this.idPedido = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}
	public Workstation getWsActual() {
		return wsActual;
	}
	public void setWsActual(Workstation wsActual) {
		this.wsActual = wsActual;
	}
	public Workstation getWsDestino() {
		return wsDestino;
	}
	public void setWsDestino(Workstation wsDestino) {
		this.wsDestino = wsDestino;
	}


	@Override
	public String toString() {
		return  " "+id;
	}

	
	
}
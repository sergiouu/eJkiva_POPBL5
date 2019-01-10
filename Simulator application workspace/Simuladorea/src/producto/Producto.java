package producto;

import circuito.Workstation;

public class Producto {
	int id;
	String description;
	Workstation wsActual, wsDestino;

	
	public Producto(int id, String description, Workstation ws) {
		this.id=id;
		this.description=description;
		this.wsActual = ws;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public Workstation getWsActual() {
		return wsActual;
	}
	public void setWsActual(Workstation wsActual) {
		this.wsActual = wsActual;
	}

	@Override
	public String toString() {
		return id+"."+description;
	}

	
	
}
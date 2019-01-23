package producto;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	int id, cantidadProductos;
	List<Producto> listaProductos;
	Date fechaSalida;
	
	
	public Pedido() {
		
	}
	
	@Override
	public String toString() {
		return "pedido-id:"+id;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void añadirProducto(Producto p) {
		listaProductos.add(p);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidadProductos;
	}
	
	public List<Producto> getProducto() {
		return listaProductos;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidadProductos = cantidad;
	}

}

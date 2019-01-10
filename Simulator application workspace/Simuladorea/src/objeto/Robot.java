package objeto;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import circuito.Workstation;
import producto.AdministradorDeProductos;
import producto.Producto;

public class Robot extends Thread {
	
	private final Lock lock = new ReentrantLock();
	private final Condition apagado=lock.newCondition();
	private final Condition esperandoAccesoWorkstation = lock.newCondition();

	
	
	int id;
	boolean encendido;
	String description;
	Producto producto;
	boolean cargado;
	Workstation wsActual, wsDestino;
	

	public Robot(int id, String description, Workstation actual) {
		this.id=id;
		this.description = description;
		this.producto=null;
		this.encendido=false;
		this.wsActual = actual;
		this.cargado = false;
	}
	
	public void descargarProducto() {
		this.producto = null;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void cargarProducto(Producto producto) {
		this.producto = producto;
	}

	public long getId() {
		return id;
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
		return  id+"."+description+"\n" + wsActual;
	}	
	
	public void apagarRobot() {
		lock.lock();
		try {
			this.encendido = false;
			System.out.println("Apagado el robot...");
			this.apagado.await();
			System.out.println("Encendiendo el robot...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	
	public void encenderRobot() {
		lock.lock();
		System.out.println("Encendiendo robot");
		this.apagado.signal();
		this.encendido = true;
		lock.unlock();
	}


	@Override
	public void run() {
		boolean ejecucion = true;
		
		do {
			
		//	isEnDestino();	
		//comprobarSiTieneCargarEnWorkstation
		//comprobarSi tiene que dejar
		/*	if(tiene que dejarlo){
		 * lo dejas
		 * apagar robot
		 * }
		 * else{
		 * continuas el viaje:
		 * comprobar siguiente paso
		 * si no esta libre: wait(esperaraAccesoWorkstation)}
		 * moverlo al siguiente workstation
		 */
		}while(ejecucion);
	}
	
	
}

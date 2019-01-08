package Objeto;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Circuito.Producto;
import Circuito.Workstation;
import Producto.AdministradorDeProductos;

public class Robot extends Thread {
	private final Lock lock = new ReentrantLock();
	private final Condition apagado=lock.newCondition();
	
	
	int id;
	int posX, posY;
	String description;
	Producto producto;
	//Workstation wsActual, wsDestino;
	boolean encendido;
	
	AdministradorDeProductos ap;
	
	public Robot(int id,int posX,int posY, String description,AdministradorDeProductos ap) {
		this.id=id;
		this.posX = posX;
		this.posY=posY;
		this.description = description;
		this.producto=null;
		this.encendido=false;
		this.ap=ap;
	}
	
	public void descargarProducto() {
		this.producto = null;
	}
	
	public Producto getProducto() {
		return producto;
	}


	public void asignaProductoARobot(Producto producto) {
		this.producto = producto;
	}


	public long getId() {
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
	@Override
	public String toString() {
		return "PosX: "+ this.posX +"\n" +
		        "PosY: " + this.posY +"\n";
	}	
	
	/*private void comprobarSiguientePaso(){
		if(producto == null ) {
			
			if(ap.hayPaquetesEsperando(this)) {
				System.out.println("TREN: "+this.getNombre()+" dispuesto a seguir por que hay un paquete esperandole en algun sitio");
				repostar();
			}else {
				System.out.println("Se apaga el tren");
				setSQLestadoTren("apagado");
				apagarTren();
			}
			
		}else {
			repostar();
		}
	}*/
	
	public void apagarRobot() {
		lock.lock();
		try {
			this.encendido = false;
			System.out.println("Apagado el robot");
			this.apagado.await();
			System.out.println("Encendido el robot");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	
	public synchronized void encenderRobot() {
		lock.lock();
		System.out.println("Encendiendo robot");
		this.apagado.signal();
		this.encendido = true;
		lock.unlock();
	}


	@Override
	public void run() {
		boolean funcionando = true;
		
		do {
			
			
			
			
			
		}while(funcionando);
	}
}

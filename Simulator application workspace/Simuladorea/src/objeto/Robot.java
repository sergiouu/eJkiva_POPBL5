package objeto;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import circuito.Workstation;
import movimiento.Mover;
import producto.Producto;

public class Robot extends Thread {
	
	private final Lock lock = new ReentrantLock();
	private final Condition apagado=lock.newCondition();
	private final Condition waitAccesoWorkstation = lock.newCondition();
	
	int id;
	boolean encendido;
	String description;
	Producto producto;
	boolean cargado;
	Workstation wsActual,wsCogerProducto, wsDestino, wsSiguiente;
	List <Workstation> listaAllWorstations;

	public Robot(int id, String description, Workstation actual, List<Workstation> listaAllWs) {
		this.id=id;
		this.description = description;
		this.producto=null;
		this.encendido=false;
		this.wsActual = actual;
		this.cargado = false;
		this.listaAllWorstations = listaAllWs;
		this.wsCogerProducto = null;
		this.wsDestino = null;
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

	public Workstation getWsSiguiente() {
		return wsSiguiente;
	}

	public void setWsSiguiente(Workstation wsSiguiente) {
		this.wsSiguiente = wsSiguiente;
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
			isEnDestino();	//Si esta en destino deja el paquete
			
			cargarSiPuede();
			//Si el robot va vacío comprueba si hay algun producto q coger, y lo coge
		
			comprobarSiguienteWs(); //solo tiene que mirar si esta ocupado si el ws siguiente es es el final porque el camino está siempre libre
			mover();
		
		}while(ejecucion);
	}
	
	private void cargarSiPuede() {
		if(isEnWsCarga()) {
			cogerProductoDeWs();
		}
	}

	private boolean isEnWsCarga() {
		if(wsActual.equals(wsCogerProducto)) return true;
		return false;
	}

	private void mover() {
		wsActual = wsSiguiente;
	}

	/*	if(tiene que dejarlo){
	 * lo dejas
	 * apagar robot
	 * }
	 * else
	 * continuas el viaje:
	 * comprobar siguiente paso
	 * si no esta libre: wait(esperaraAccesoWorkstation)}
	 * moverlo al siguiente workstation
	 */
	
	private void comprobarSiguienteWs() {
		calcularSiguientePaso();
		
		
	}
	
	public void notificarAcceso() {
		lock.lock();
		System.out.println("PERMISO CONCEDIDO A WS");
		this.waitAccesoWorkstation.signal();
		lock.unlock();
	}

	public void waitAccesoWs() {
		lock.lock();
		try {
			System.out.println("ESPERANDO ACCESO A WS");
			this.waitAccesoWorkstation.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	void isEnDestino() {
		if(this.wsActual.equals(this.wsDestino)) {
			this.dejarProductoEnWs();
			this.apagarRobot();
		}
	}
	
	void dejarProductoEnWs() {
		this.getWsActual().añadirProducto(this.producto);
		this.producto = null;
		this.cargado = false;
	}
	
	void cogerProductoDeWs() {
		for(Producto p : this.getWsActual().getListaProductos()) {
			if(p.getId() == this.producto.getId()) {
				this.getWsActual().getListaProductos().remove(p);
			}
		}
		this.producto=null;
		this.cargado=true;
	}
	
	public void calcularSiguientePaso() {
		//elena hau da threadendako posizinua
    	//cada vez que se mueve if ian sartzen da!
		Mover mover =new Mover(this, listaAllWorstations);
		int finPosX=this.getWsDestino().getPosX();
		int finPosY=this.getWsDestino().getPosY();
		int xValue;
		int yValue;
		boolean amaiera=false;
		//do {
			xValue=this.getWsActual().getPosX()-finPosX;
			yValue=this.getWsActual().getPosY()-finPosY;
			
			if(xValue>0 && yValue>0) {mover.xPosyPos();}
			else if(xValue>0 && yValue<0) {mover.xPosyNeg();}
			else if(xValue>0 && yValue==0) {mover.xPosyZero();}
			else if(xValue<0 && yValue>0) {mover.xNegyPos();}
			else if(xValue<0 && yValue<0) {mover.xNegyNeg();}
			else if(xValue<0 && yValue==0) {mover.xNegyZero();}
			else if(xValue==0 && yValue>0) {mover.xZeroyPos();}
			else if(xValue==0 && yValue<0) {mover.xZeroyNeg();}
			else if(xValue==0 && yValue==0) {amaiera=true;}
		//}while(!amaiera);	
	}
}
	


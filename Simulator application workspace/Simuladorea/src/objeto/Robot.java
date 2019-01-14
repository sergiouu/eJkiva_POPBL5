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
	private final Condition waitAccesoCamino = lock.newCondition();
	int kont=0;
	int id;
	boolean encendido;
	String description;
	Producto producto;
	Workstation wsActual,wsCogerProducto, wsDestino, wsSiguiente;
	List <Workstation> listaAllWorstations;

	public Robot(int id, String description, Workstation actual, List<Workstation> listaAllWs) {
		this.id=id;
		this.description = description;
		this.producto=null;
		this.encendido=false;
		this.wsActual = actual;
		this.listaAllWorstations = listaAllWs;
		this.wsCogerProducto = null;
		this.wsDestino = null;
	}
	
	
	public Workstation getWsCogerProducto() {
		return wsCogerProducto;
	}


	public void setWsCogerProducto(Workstation wsCogerProducto) {
		this.wsCogerProducto = wsCogerProducto;
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
		return "robot:"+ id+"."+description+"wsActual:" + wsActual.getDescription();
	}	
	
	public void apagarRobot() {
		lock.lock();
		try {
			this.encendido = false;
			System.out.println("Apagado el robot...");
			this.apagado.await();
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
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calcularSiguientePaso(listaAllWorstations);
			if(this.wsActual.isStateOutside()) {
				viajandoEnCircuito();
			}
			else {
				estaEnWorkstation();
			}	
		}while(ejecucion);
	}
	
	 public void calcularSiguientePaso(List<Workstation> workstationList) {
			//elena hau da threadendako posizinua
	    	//cada vez que se mueve if ian sartzen da!
		 Mover mover =new Mover(this, workstationList);
//		 System.out.println(this + "Destino 1:" + this.getWsCogerProducto());
		 int finPosX=this.getWsCogerProducto().getPosX();
		 int finPosY=this.getWsCogerProducto().getPosY();
		 int xValue;
		 int yValue;
		 boolean amaiera=false;
		//	do {
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
		//	}while(!amaiera);	
			}

	private void viajandoEnCircuito() {
		comprobarSiEstaLibreDentro();
		comprobarSiEstaLibreFuera();
		mover();
		cargarSiDebe();
	}


	private void comprobarSiEstaLibreFuera() {
		if(wsSiguiente.isStateOutside()) {
			wsSiguiente.setOutsideEspera(this);
		}
		waitAccesoCamino();
	}


	private void comprobarSiEstaLibreDentro() {
		if(wsSiguiente.isStateInside()) {
			wsSiguiente.setInsideEspera(this);
			desalojarWs();
			waitAccesoWs();
		}
	}

	private void desalojarWs() {
		wsSiguiente.getInside().setWsDestino(getWorkstationVacio());
		wsSiguiente.getInside().encenderRobot();
	}

	private Workstation getWorkstationVacio() {
		boolean assigned = false;
		Workstation wsVacio = null;
		
		for(id = this.getWsActual().getId(); id <= listaAllWorstations.size(); id++) {
			if(!getWorkstationById(id).isStateInside() && !assigned) {
				wsVacio = getWorkstationById(id);
				assigned = false;
			}
		}
		
		if(!assigned) {
			for(id = this.getWsActual().getId(); id < this.getWsActual().getId() ; id++) {
				if(!getWorkstationById(id).isStateInside() && !assigned) {
					wsVacio = getWorkstationById(id);
					assigned = false;
				}
			}
		}
		
		return wsVacio;
	}

	public Workstation getWorkstationById(int id) {
		Workstation ws= null;
		for(Workstation w : listaAllWorstations) {
			if(w.getId() == id) {
				ws = w;
			}
		}
		return ws;
	}
	
	private void estaEnWorkstation() {
		
		if(wsActual.isStateOutside()) {
			wsActual.setOutsideEspera(this);
			this.waitAccesoCamino();
			wsActual.setOutside(this);
			wsActual.setOutsideEspera(null);
			wsActual.setInside(null);
			this.notificarAccesoCamino();
		}
		
	}

	private void cargarSiDebe() {
		if(wsActual.equals(wsCogerProducto)) {
			wsActual.setOutside(null);
			wsActual.setInside(this);
			cogerProductoDeWs();
		}
	}

	@SuppressWarnings("static-access")
	private void mover() {
		System.out.println(this.getId() + "se mueve a" + this.getWsActual());
		wsActual = wsSiguiente;
		wsSiguiente.setOutside(this);
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificarAccesoCamino();
	}



	
	private void waitAccesoCamino() {
		lock.lock();
		System.out.println("Esperando acceso al camino");
		try {
			this.waitAccesoCamino.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	private void notificarAccesoCamino() {
		lock.lock();
		System.out.println("Permiso concedido a ws");
		this.waitAccesoCamino.signal();
		lock.unlock();
	}

	public void waitAccesoWs() {
		lock.lock();
		try {
			System.out.println("Esperando acceso a ws");
			this.waitAccesoWorkstation.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}

	
	public void notificarAccesoWs() {
		lock.lock();
		System.out.println("Permiso concedido a ws");
		this.waitAccesoWorkstation.signal();
		lock.unlock();
	}
	
	void isEnDestino() {
		if(wsActual.equals(this.wsDestino) || wsCogerProducto == null && wsDestino == null) {
			dejarProductoEnWs();
			apagarRobot();
		}
	}
	
	void dejarProductoEnWs() {
		this.getWsActual().añadirProducto(this.producto);
		this.producto = null;
	}
	
	void cogerProductoDeWs() {
		for(Producto p : this.getWsActual().getListaProductos()) {
			if(p.getId() == this.producto.getId()) {
				this.producto = p;
				this.getWsActual().getListaProductos().remove(p);
			}
		}
	}
	
	public void calcularSiguientePaso(Workstation destino) {
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
	


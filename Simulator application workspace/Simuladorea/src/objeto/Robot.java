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
	Workstation wsActual, wsCogerProducto, wsDestino, wsSiguiente;
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
			
		//	System.out.println("Apagado el robot...");
			this.apagado.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void encenderRobot() {
		lock.lock();
	//	System.out.println(this + "Encendiendo robot...");
		this.apagado.signal();
		this.encendido = true;
		lock.unlock();
	}

	@Override
	public void run() {
		
		boolean ejecucion = true;
		do {
			
			isEnDestino();	//Si esta en destino deja el paquete
		
			
			calcularSiguientePaso(listaAllWorstations);
			if(this.wsActual.isStateOutside()) {
			//	System.out.println("viajando en circuito");
				viajandoEnCircuito();
			}
			else {
			//	System.out.println("esta en ws");
				estaEnWorkstation();
			}	
		}while(ejecucion);
	}
		 
	private void calcularSiguientePaso(List<Workstation> listaAllWorstations2) {
	 	//cada vez que se mueve if ian sartzen da!
		 Mover mover =new Mover(this, listaAllWorstations2);
		 int finPosX;
		 int finPosY;
		 int xValue;
		 int yValue;
		 
		 if(this.getWsCogerProducto()!=null) {
			   finPosX=this.getWsCogerProducto().getPosX();
			   finPosY=this.getWsCogerProducto().getPosY();
			  
			 }
	     else {
			   finPosX=this.getWsDestino().getPosX();
			   finPosY=this.getWsDestino().getPosY();
	     }
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
				else if(xValue==0 && yValue==0) {amaiera=true;
				}
			//}while(!amaiera);	
			
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
		//	System.out.println(this.wsSiguiente);
		//	System.out.println(this + "ESPEROOOOO CAMINO");
			waitAccesoCamino();
		}
	}
	
	private void comprobarSiEstaLibreDentro() {
		if(wsSiguiente.isStateInside()) {
			wsSiguiente.setInsideEspera(this);
			desalojarWs();
		//	System.out.println(this + " ESPERARWS en :"+this.wsActual);
			waitAccesoWs();
		}
	}

	private void desalojarWs() {
		wsSiguiente.getInside().setWsCogerProducto(null);
		wsSiguiente.getInside().setWsDestino(getWorkstationVacio());
	//	System.out.println("---------------------------desalojando robot:"+ wsSiguiente.getInside() +" a:" + wsSiguiente.getInside().getWsDestino());
		wsSiguiente.getInside().encenderRobot();
	}

	private Workstation getWorkstationVacio() {
		boolean assigned = false;
		Workstation wsVacio = null;
		
		for(int n = wsSiguiente.getId(); n <= listaAllWorstations.size(); n++) {
			if(!getWorkstationById(n).isStateInside() && !assigned) {
	//			System.out.println(getWorkstationById(n) + "-"+ getWorkstationById(n).isStateInside());
				wsVacio = getWorkstationById(n);
				assigned = true;
			}
		}
		
		if(!assigned) {
			for(int n = 1; n < wsSiguiente.getId() ; n++) {
				if(!getWorkstationById(n).isStateInside() && !assigned) {
					wsVacio = getWorkstationById(n);
					assigned = true;
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
		comprobarSiEstaLibreFuera();
		moverDentroAFuera();
	}

	private void moverDentroAFuera() {
		wsActual.setOutside(this);
		wsActual.setInside(null);
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificarAccesoWs();
	}

	private void cargarSiDebe() {
		if(wsActual.equals(wsCogerProducto)) {
			entrarDentro();
			cogerProductoDeWs();
			wsCogerProducto = null;
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	System.out.println(this + "ha cogido:" + this.producto);
		}
	}

	private void entrarDentro() {
		wsActual.setOutside(null);
		wsActual.setInside(this);
	}

	@SuppressWarnings("static-access")
	private void mover() {
		Workstation wsAnterior;
		wsAnterior = wsActual;
		wsActual.setOutside(null);
	//	System.out.println(this+ "se mueve a" + this.getWsSiguiente().getDescription());
		wsActual = wsSiguiente;
		
		wsSiguiente.setOutside(this);
		wsSiguiente =null;
		
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificarAccesoCamino(wsAnterior);
	}

	private void waitAccesoCamino() {
		lock.lock();
	//	System.out.println("WAAAAAAAAAAAIT CAMINO");
	//	System.out.println(this.wsSiguiente);
	//	System.out.println(this + "Esperando acceso al camino");
		try {
			this.waitAccesoCamino.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	private void notificarAccesoCamino(Workstation wsAnterior) {
		lock.lock();
		if(wsAnterior.getOutsideEspera()!= null) {
		//	System.out.println("Notificar acceso a: " + wsAnterior.getOutsideEspera());
			wsAnterior.getOutsideEspera().despertarOutside();
			waitAccesoCamino.signal();
		}
		lock.unlock();
	}

	public void despertarOutside()
	{
		lock.lock();
		waitAccesoCamino.signal();
		lock.unlock();
	}
	
	public void waitAccesoWs() {
		lock.lock();
		try {
		//	System.out.println("Esperando acceso a ws");
			this.waitAccesoWorkstation.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}

	public void notificarAccesoWs() {
	//	lock.lock();
			if(wsActual.getInsideEspera() != null) {
				wsActual.getInsideEspera().despertarInside();
			
			}
	//	lock.unlock();
	}
	
	public void despertarInside()
	{
		lock.lock();
		waitAccesoWorkstation.signal();
		lock.unlock();
	}
	
	
	void isEnDestino() {
		if(wsActual.equals(this.wsDestino)) {
			dejarProductoEnWs();
			apagarRobot();
		}
		if( wsCogerProducto == null && wsDestino == null) {
			apagarRobot();
		}
	}
	
	void dejarProductoEnWs() {
		this.getWsActual().setInside(this);
		this.getWsActual().setOutside(null);
		if(this.producto!=null) {
			this.getWsActual().añadirProducto(this.producto);
			System.out.println(this + "deja:" + this.producto);
		}
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
	
}
	


package circuito;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import SQLConnector.SQLConnector;
import movimiento.Mover;
import producto.AdministradorDeProductos;
import producto.Pedido;
import producto.Producto;

public class Robot extends Thread {
	
	private final Lock lock = new ReentrantLock();
	private final Condition apagado=lock.newCondition();
	private final Condition waitAccesoWorkstation = lock.newCondition();
	private final Condition waitAccesoCamino = lock.newCondition();
	int kont=0, kontador=0;
	int id;
	
	boolean encendido;
	String description;
	Producto producto;
	Workstation wsActual, wsCogerProducto, wsDestino, wsSiguiente;
	List <Workstation> listaAllWorstations;
	AdministradorDeProductos ap;
	SQLConnector conexion;
	
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
	public void setAp(AdministradorDeProductos ap) {
		this.ap = ap;
	}
	public AdministradorDeProductos getAp() {
		return ap;
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
				viajandoEnCircuito();
			}
			else {
				estaEnWorkstation();
			}	
		}while(ejecucion);
	}
	

	private void viajandoEnCircuito() {
		comprobarWsSiguienteInside();
		comprobarWsSiguienteOutside();
		moverAWsSiguiente();
		cargarSiDebe();
	}

	private void comprobarWsSiguienteOutside() {

		if(wsSiguiente.isStateOutside()) {
			wsSiguiente.setOutsideEspera(this);
			waitAccesoCamino();
		}
	}
	
	private void comprobarWsSiguienteInside() {
		if(wsSiguiente.isStateInside()) {
			wsSiguiente.setInsideEspera(this);
			if(!wsSiguiente.getInside().encendido) {
				desalojarWs();
			}
			
			waitAccesoWs();
		}
	}

	private void desalojarWs() {
	
		wsSiguiente.getInside().setWsCogerProducto(null);
		wsSiguiente.getInside().setWsDestino(getWorkstationVacio());
		wsSiguiente.getInside().encenderRobot();
	}

	private void estaEnWorkstation() {
	//	System.out.println(this+" esta en workstation");
		comprobarWsSiguienteOutside();
		salirDeWorkstation();
	}

	private void salirDeWorkstation() {
		wsActual.setOutside(this);
		wsActual.setInside(null);
		//SALIENDO//
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notificarAccesoWs();
		System.out.println("saliendooooo" + wsActual.outside);
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
			//coge el producto
			System.out.println(this + "ha cogido:" + this.producto);
		}
	}

	private void entrarDentro() {
		wsActual.setOutside(null);
		wsActual.setInside(this);
	}

	@SuppressWarnings("static-access")
	private void moverAWsSiguiente() {
	//	System.out.println("MOVER A SIGUIENTE");
		Workstation wsAnterior;
		wsSiguiente.setOutside(this);
		wsActual.setOutside(null);
		wsAnterior = wsActual;
		//Moviendo//
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsActual = wsSiguiente;
		wsSiguiente = null;
		
		System.out.println("ME HE MOVIDO" + wsActual + wsDestino + wsCogerProducto);
		notificarAccesoCamino(wsAnterior);
	}

	private void waitAccesoCamino() {
		lock.lock();
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
		lock.lock();
			if(wsActual.getInsideEspera() != null) {
				wsActual.getInsideEspera().despertarInside();
			
			}
		lock.unlock();
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
		//	this.getWsActual().añadirProducto(this.producto);
		
			for (Iterator<Pedido> p = ap.getListaPedidos().iterator(); p.hasNext(); ) {
			    
				System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				Pedido pedido = p.next();
			    if(pedido.getId() == producto.getIdPedido()) {
			    	System.out.println("SARTUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU: "+pedido.getCantidad());
			    	int c = pedido.getCantidad();
			    	pedido.setCantidad(c-1);
			    	System.out.println("SARTUUUU: "+pedido.getCantidad());

			    	if(pedido.getCantidad()==0) {
			    		System.out.println(producto.getId() + "peretenece a" + pedido.getId() );
			    		ap.eliminarPedidosDeLista(pedido);
			    		conexion = new SQLConnector();
			    		conexion.setDelievered(pedido);
			    		
			    		break;
			    	} 
			    }
			}
			
			System.out.println(this + "deja:" + this.producto);
		}
		this.producto = null;
	}
	
	void cogerProductoDeWs() {
		for(Producto p : this.getWsActual().getListaProductos()) {
			if(p.getId() == this.producto.getId()) {
				this.producto = p;
			}
		}
	}
	 
	private void calcularSiguientePaso(List<Workstation> listaAllWorstations2) {

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
		
				xValue=this.getWsActual().getPosX()-finPosX;
				yValue=this.getWsActual().getPosY()-finPosY;
			
				if(this.getId()==3){
					
					System.out.println("Actual"+this.getWsActual().getPosX()+this.getWsActual().getPosY()+this.getWsActual().getDescription());
					System.out.println("Destino1"+this.getWsCogerProducto().getPosX()+this.getWsCogerProducto().getPosY()+
							this.getWsCogerProducto().getDescription());
				}
				if(xValue>0 && yValue>0) {mover.xPosyPos();}
				else if(xValue>0 && yValue<0) {mover.xPosyNeg();}
				else if(xValue>0 && yValue==0) {mover.xPosyZero();}
				else if(xValue<0 && yValue>0) {mover.xNegyPos();}
				else if(xValue<0 && yValue<0) {mover.xNegyNeg();}
				else if(xValue<0 && yValue==0) {mover.xNegyZero();}
				else if(xValue==0 && yValue>0) {mover.xZeroyPos();}
				else if(xValue==0 && yValue<0) {mover.xZeroyNeg();}
				else if(xValue==0 && yValue==0) {}
			
	}
	
	public Workstation getWorkstationVacio() {
		boolean assigned = false;
		Workstation wsVacio = null;
		
		for(int n = wsSiguiente.getId(); n <= listaAllWorstations.size(); n++) {
			if(!getWorkstationById(n).isStateInside() && !assigned) {
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
	
}
	


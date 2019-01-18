package circuito;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import movimiento.Mover;
import producto.Producto;


/**
*
* <h2> Robot class </h2>
* <p>
* Manages all the information about the Robot</p>
*
*@extends Thread
*
* @author elarretegui 09/Jan/2019
*/
public class Robot extends Thread {
	
	private final Lock lock = new ReentrantLock();
	private final Condition apagado=lock.newCondition();
	
	int kont=0;
	int kontador = 0;
	int id;
	boolean encendido;
	String description;
	Producto producto;
	boolean cargado;
	Workstation wsActual, wsCogerProducto, wsDestino;
	public Workstation wsSiguiente;
	Workstation wsAnterior;
	List <Workstation> listaAllWorstations;
	Circuito circuito;
	
	/**
	*
	* <h2> Robot </h2>
	* <p>
	* Constructor of class Robot</p>
	*
	*@param id, description, actual, listaAllWs, circuito
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Robot(int id, String description, Workstation actual, List<Workstation> listaAllWs, Circuito circuito) {
		this.id=id;
		this.description = description;
		this.producto=null;
		this.encendido=false;
		this.wsActual = actual;
		this.listaAllWorstations = listaAllWs;
		this.wsCogerProducto = null;
		this.wsDestino = null;
		this.circuito = circuito;
	}
	
	/**
	*
	* <h2> getWsCogerProducto </h2>
	* <p>
	* Get the product that the robot has assigned</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Workstation getWsCogerProducto() {
		return wsCogerProducto;
	}
	
	/**
	*
	* <h2> setWsCogerProducto </h2>
	* <p>
	* set the product that the robot has assigned</p>
	*
	*@param wsCogerProducto
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setWsCogerProducto(Workstation wsCogerProducto) {
		this.wsCogerProducto = wsCogerProducto;
	}

	/**
	*
	* <h2> descargarProducto </h2>
	* <p>
	* empty the robot</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/

	public void descargarProducto() {
		cargado = false;
	}
	
	/**
	*
	* <h2> getProducto </h2>
	* <p>
	* Get the product that the robot has assigned</p>
	*
	*@return Producto
	*
	* @author elarretegui 09/Jan/2019
	*/

	public Producto getProducto() {
		return producto;
	}

	/**
	*
	* <h2> cargarProduct </h2>
	* <p>
	* The robots get the information of its wsCogerProducto and ws Destino from the product assigned, and also a product is assigned</p>
	*
	*@param producto
	*
	* @author elarretegui 09/Jan/2019
	*/

	public void cargarProducto(Producto producto) {
		this.setWsCogerProducto(producto.getWsActual());
		this.setWsDestino(producto.getWsDestino());
		this.producto = producto;
		System.out.println(this + ":cargar producto");

	}

	/**
	*
	* <h2> getID </h2>
	* <p>
	* Get id of the robot</p>
	*
	*@return int
	*
	* @author elarretegui 09/Jan/2019
	*/

	public long getId() {
		return id;
	}
	
	/**
	*
	* <h2> getWsActual </h2>
	* <p>
	* Get actual workstation in which is the robot</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/

	public Workstation getWsActual() {
		return wsActual;
	}

	/**
	*
	* <h2> getWsActual </h2>
	* <p>
	* Get actual workstation in which is the robot</p>
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
	* Get the workstation in which the robot has to leave the product</p>
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
	* <h2> setWsDctual </h2>
	* <p>
	* Set workstation in which is the robot has to leave the product</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setWsDestino(Workstation wsDestino) {
		
	//	System.out.println(this + "-----DESTINO----- ALDATZEN:" + wsDestino);
	//	this.setWsCogerProducto(producto.getWsActual()); //CAMBIAR
	//	setWsDestino(producto.getWsDestino()); //CAMBIAR
		this.wsDestino = wsDestino;
	}

	/**
	*
	* <h2> getWsSiguiente </h2>
	* <p>
	* Get the next workstation which has to achive to achive the destination </p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Workstation getWsSiguiente() {
		return wsSiguiente;
	}

	/**
	*
	* <h2> setWsSiguiente </h2>
	* <p>
	* Set the next workstation the robot has to achieve</p>
	*
	*@param wsSiguiente
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void setWsSiguiente(Workstation wsSiguiente) {
		this.wsSiguiente = wsSiguiente;
	}

	/**
	*
	* <h2> toString </h2>
	* <p>
	* toString method of Robot class</p>
	*
	*@return string
	*
	* @author elarretegui 09/Jan/2019
	*/
	@Override
	public String toString() {
		return "robot:"+ id+"."+description+"wsActual:" + wsActual.getDescription();
	}	
	
	/**
	*
	* <h2> apagarRobot </h2>
	* <p>
	* Turn off the robot</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	
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
	
	/**
	*
	* <h2> encenderRobot </h2>
	* <p>
	* Turn on the robot</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void encenderRobot() {
		lock.lock();
	//	System.out.println(this + "Encendiendo robot...");
		this.apagado.signal();
		this.encendido = true;
		lock.unlock();
	}

	/**
	*
	* <h2> run </h2>
	* <p>
	* run method of Robot: 
	* 1. checks if it has reached the destination
	* 2. calculates next workstation
	* 3. moves to the next workstation</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	@Override
	public void run() {
		
		boolean ejecucion = true;
		do {
		
			  isEnDestino();	//Si esta en destino deja el paquete	
			  calcularSiguientePaso(listaAllWorstations);
			  if(this.wsActual.isStateOutside()) {
				  circuito.viajando(this);
			  }
			  else {
				  try {
					sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  circuito.enWorkstation(this);
			  }
			
		}while(ejecucion);
	}
	/**
	*
	* <h2> calcularSiguientePaso </h2>
	* <p>
	* Calculates which is going to be the next workstation the robot has to move</p>
	*
	*@param listaAllWorkstations
	*
	* @author elarretegui 09/Jan/2019
	*/
	private void calcularSiguientePaso(List<Workstation> listaAllWorstations) {
	 	//cada vez que se mueve if ian sartzen da!
		 Mover mover =new Mover(this, listaAllWorstations);
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
	}

	/**
	*
	* <h2> desalojarWS </h2>
	* <p>
	* Sets a new destination to the </p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void desalojarWs() {
		wsSiguiente.getInside().setWsCogerProducto(null);
		wsSiguiente.getInside().setWsDestino(getWorkstationVacio());
		wsSiguiente.getInside().encenderRobot();
	}

	/**
	*
	* <h2> getWsActual </h2>
	* <p>
	* Gets an empty workstation</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Workstation getWorkstationVacio() {
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

	/**
	*
	* <h2> getWsActual </h2>
	* <p>
	* Get actual workstation in which is the robot</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public Workstation getWorkstationById(int id) {
		Workstation ws= null;
		for(Workstation w : listaAllWorstations) {
			if(w.getId() == id) {
				ws = w;
			}
		}
		return ws;
	}
	
	/**
	*
	* <h2> moverDentroAFuera </h2>
	* <p>
	* Move from the inside of the workstation to the outside</p>
	*
	*@param id
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void moverDentroAFuera() 
	{
		
		wsActual.setOutside(this);
		wsActual.setInside(null);
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	*
	* <h2> cargarSiDebe </h2>
	* <p>
	* Checks if the workstation in which is, is the one in which it has to pick up a product
	* If it is, it gets inside and picks it up </p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void cargarSiDebe() {
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
			System.out.println(this + "ha cogido:" + this.producto);
		}
	}
	/**
	*
	* <h2> entrarDentro </h2>
	* <p>
	* The robot gets inside the workstation</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/

	private void entrarDentro() {
		wsActual.setInside(this);
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsActual.setOutside(null);
		
	}

	/**
	*
	* <h2> isEnDestino </h2>
	* <p>
	* Checks if the robot has reached the destination
	* if it has, leave the product and turn of the robot</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	void isEnDestino() {
		if(wsActual.equals(this.wsDestino)) {
			dejarProductoEnWs();
			apagarRobot();
		}
		if( wsCogerProducto == null && wsDestino == null) {
			apagarRobot();
		}
	}
	/**
	*
	* <h2> dejarProductoEnWs </h2>
	* <p>
	* leaves the product in workstation</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	void dejarProductoEnWs() {
		entrarDentro();
		if(this.producto!=null) {
			this.getWsActual().añadirProducto(this.producto);
			System.out.println(this + "deja:" + this.producto);
			
		}
		this.producto = null;
		
	}
	
	/**
	*
	* <h2> cogerProductoDeWs </h2>
	* <p>
	* Takes a product from the workstation</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	void cogerProductoDeWs() {
		for(Producto p : this.getWsActual().getListaProductos()) {
			if(p.getId() == this.producto.getId()) {
				this.cargado = true;
			//	this.getWsActual().getListaProductos().remove(p);
			}
		}
	}
	/**
	*
	* <h2> move </h2>
	* <p>
	* Moves from the actual workstation no the next one</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	@SuppressWarnings("static-access")
	public void mover() {
		wsAnterior = wsActual;
	//	System.out.println(this+ "se mueve a" + this.getWsSiguiente().getDescription());
		wsActual = wsSiguiente;
		wsSiguiente.setOutside(this);
		try {
			this.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsActual.setOutside(null);
	}
}
	


package circuito;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

import producto.AdministradorDeProductos;

/**
*
* <h2> Circuito Class </h2>
* <p>
* Manages all the information about the Road</p>
*
* @author mbenoga 09/Jan/2018
*
*/
public class Circuito {
	
	private final Lock lock = new ReentrantLock();
	private final Condition waitAccesoWorkstation = lock.newCondition();
	private final Condition waitAccesoCamino = lock.newCondition();
	List<Workstation> listaWorkstation;
	List<Robot> listaRobot;
	AdministradorDeProductos ap;
	
	/**
	*
	* <h2> Circuito </h2>
	* <p>
	* Constructor of circuito</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	
	public Circuito() {
		listaWorkstation = new ArrayList<>();
		listaRobot = new ArrayList<>();
		initWorkstations();
		initRobots();
	}
	
	/**
	*
	* <h2> addRobots </h2>
	* <p>
	* Add a robot to the list of robots</p>
	*
	*@param robot
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void addRobots(Robot robot) {		
		listaRobot.add(robot);		
	}
	
	/**
	*
	* <h2> addWorkstation </h2>
	* <p>
	* Add a workstation to the list of workstations</p>
	*
	*@param workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void addWorkstation(Workstation workstation) {		
		listaWorkstation.add(workstation);		
	}
	
	/**
	*
	* <h2> initWorkstation </h2>
	* <p>
	* Initialize all the workstations</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void initWorkstations() {
		Workstation workstation;
		
		workstation=new Workstation(1,0, 0, "Order Exit");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(3,2, 0, "Product Entry");
		listaWorkstation.add(workstation);

		workstation=new Workstation(12,0, 1, "Packaging A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(10,2, 1, "Packaging B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(8,4, 1, "Packaging C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(7,5, 1, "Packaging D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(11,1, 1, "Parking A");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(9,3, 1, "Parking B");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(4,3, 0, "Parking C");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(2,1, 0, "Parking D");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(6,5, 0, "Packaging E");
		listaWorkstation.add(workstation);
		
		workstation=new Workstation(5,4, 0, "Packaging F");
		listaWorkstation.add(workstation);
		
		
		
		System.out.println("Workstations created");
	}
	
	/**
	*
	* <h2> initRobots </h2>
	* <p>
	* Initialize all the robots</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void initRobots() {
		Robot robot1, robot2, robot3, robot4, robot5;
		
		robot1=new Robot(1,"Robot A", this.getByDescription("Parking A"), listaWorkstation, this);
		this.getByDescription("Parking A").setInside(robot1);
		listaRobot.add(robot1);
		robot1.start();
		
		robot2=new Robot(2,"Robot B",this.getByDescription("Parking B"), listaWorkstation, this);
		this.getByDescription("Parking C").setInside(robot2);
		listaRobot.add(robot2);
		robot2.start();
		
		robot3=new Robot(3,"Robot C",this.getByDescription("Parking C"), listaWorkstation, this);
		this.getByDescription("Parking C").setInside(robot3);
		listaRobot.add(robot3);
		robot3.start();
		
		robot4=new Robot(4,"Robot D", this.getByDescription("Parking D"), listaWorkstation, this);
		this.getByDescription("Parking D").setInside(robot4);
		listaRobot.add(robot4);
		robot4.start();
		
		robot5=new Robot(5,"Robot E", this.getByDescription("Product Entry"), listaWorkstation, this);
		this.getByDescription("Product Entry").setInside(robot5);
		listaRobot.add(robot5);
		robot5.start();
		
		System.out.println("Robots created");
	}
		
	/**
	*
	* <h2> getByDescription </h2>
	* <p>
	* Get a workstation from the workstations list by the description</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Workstation getByDescription(String description) {
		Workstation ws = null;
		for(Workstation w : listaWorkstation) {
			if(w.getDescription().equals((description))) {
				ws=w;
			}
		}
		return ws;
	}
	
	/**
	*
	* <h2> getById </h2>
	* <p>
	* Get a workstation from the workstations list by the id</p>
	*
	*@return Workstation
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Workstation getWorkstationById(int id) {
		Workstation ws= null;
		for(Workstation w : listaWorkstation) {
			if(w.getId() == id) {
				ws = w;
			}
		}
		return ws;
	}
	
	/**
	*
	* <h2> getRobots </h2>
	* <p>
	* Get list of all the robots </p>
	*
	*@return List<Robot>
	*
	* @author elarretegui 09/Jan/2019
	*/
	public List<Robot> getRobots(){
		List<Robot> copia = listaRobot;
		return copia;
	}
	
	/**
	*
	* <h2> getWorkstations </h2>
	* <p>
	* Get list of all  the workstations</p>
	*
	*@return List<Workstation>
	*
	* @author elarretegui 09/Jan/2019
	*/
	public List<Workstation>  getWorkstations(){
		List<Workstation> copia = listaWorkstation;
		return copia;
	}
	
	/////////////////////////////////////////////////////////////////////
	/**
	*
	* <h2> viajando </h2>
	* <p>
	* When a robot is on the road and wants to move to a another one, it checks if is empty inside and outside before moving. </p>
	* In case it is full, the thread waits until it is free.
	*@param r
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void viajando(Robot r) {
		lock.lock();
			try {
				while(accesoCamino(r)) {
					r.desalojarWs();
					waitAccesoCamino.await();			
				}
			}catch (InterruptedException e) {
				System.out.println("interrupted");		
			}
			finally{
				lock.unlock();
			}
			
		lock.lock();
			try {
				while(accesoWorkstation(r)) waitAccesoCamino.await();
				r.mover();
				waitAccesoCamino.signal();
				r.cargarSiDebe(); 
			}catch (InterruptedException e) {
				System.out.println("interrupted");		
			}
			finally {
				lock.unlock();
			}
	}

	///////////////////////////////////////////
	/**
	*
	* <h2> accesoCamino </h2>
	* <p>
	* Gets if the road is empty or not</p>
	*
	*@param r
	*
	* @author elarretegui 09/Jan/2019
	*/
	public boolean accesoCamino(Robot r) {
		if(r.getWsSiguiente().isStateInside()) return true;
		else return false;
	}
	
	/**
	*
	* <h2> accesoCamino </h2>
	* <p>
	* Gets if the workstation is empty or not</p>
	*
	*@param r
	*
	* @author elarretegui 09/Jan/2019
	*/
	public boolean accesoWorkstation(Robot r) {
		if(r.getWsSiguiente().isStateOutside()) return true;
		else return false;
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	*
	* <h2> enWorkstation </h2>
	* <p>
	* When a robot is in a workstation and want to go outside, it has to chech it
	* is it is full the thread wait until it gets empty</p>
	*
	*@param r
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void enWorkstation(Robot r) {
		lock.lock();
		try {
			while(accesoWorkstation(r)) waitAccesoWorkstation.await();
				r.moverDentroAFuera();
				waitAccesoWorkstation.signal();
		}catch (InterruptedException e) {
			System.out.println("interrupted");		
		}
		finally {
			lock.unlock();
		}
	}
	/**
	*
	* <h2> setAp </h2>
	* <p>
	* Set the product manager of the class circuit</p>
	*
	*@param ap
	*
	* @author elarretegui 09/Jan/2019
	*/
	/////////////////////////////////////////////////////////////////////////////////
	public void setAp(AdministradorDeProductos ap) {
		this.ap = ap;
	}
}
package Robot;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.fabric.xmlrpc.base.Array;

import Move.Movimiento;
import Packages.Packages;
import Packages.Supervisor;
import Road.Road;

public class Robot extends Thread{
	
	int posX;
	int posY;
	boolean amaiera;
	
	int id;
	String namee;
	boolean on, access;
	
	AdministradorDeProductos ap;
	
	private final Lock lock = new ReentrantLock();
	private final Condition disconnect = lock.newCondition();
	private final Condition waitingAcces = lock.newCondition();
	private final Condition waiting = lock.newCondition();
	
	Workstation wsActual, wsDest;
	public ArrayList<Packages> packages;
	Supervisor supervisor;

	//SQLConnector connection;

	public Robot(int id, String name, boolean on, Workstation wsActual, AdministradorDeProductos ap) {
		this.id = id;
		this.namee = name;
		this.on = on;
		this.wsActual = wsActual;
		this.packages = new ArrayList<Packages>();
		this.access = false;
		
		/*this.posX = posX;
		this.posY=posY;
		amaiera=false;*/
		
		this.posX = 0;
		this.posY=0;
		amaiera=false;
		
		this.wsDest = new Workstation(1, "WS1");
		
		//this.connection = new SQLConnector();

	}
	
	@Override
	public String toString() {
		return "PosX: "+ this.posX +"\n" +
		        "PosY: " + this.posY +"\n";
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
	
	public int getRobotId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getNamee() {
		return namee;
	}

	public void setNamee(String namee) {
		this.namee = namee;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Workstation getWsActual() {
		return wsActual;
	}

	public void setWsActual(Workstation wsActual) {
		this.wsActual = wsActual;
	}

	public Workstation getWsDest() {
		return wsDest;
	}

	public void setWsDest(Workstation wsDest) {
		this.wsDest = wsDest;
	}

	public void waiting() {
		lock.lock();
		try {
			System.out.println("WAITING...");
			this.waiting.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void waitAccess() {
		lock.lock();
		try {
			System.out.println("WAITING TO HAVE ACCESS...");
			this.waitingAcces.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public void notifyWaitingRobot() {
		lock.lock();
		this.waiting.signal();
		lock.unlock();
		System.out.println("WAITING ROBOT NOTIFIED!");
	}
	
	public synchronized void notifyWaitingAccessRobot() {
		lock.lock();
		this.waitingAcces.signal();
		lock.unlock();
		System.out.println("ACCESS GIVEN!");
	}
	
	public void disconnectRobot() {
		lock.lock();
		try {
			this.on = false;
			System.out.println("ROBOT DISCONNECTED...");
			this.disconnect.await();
			System.out.println("ROBOT ON!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	public synchronized void wakeUpRobot() {
		lock.lock();
		System.out.println("ROBOT ON!");
		this.disconnect.signal();
	}
	
	private void chargePackage(Packages p) {
		this.packages.add(p);
		System.out.println("Package "+p.getId()+" assigned to "+this.getNamee()+" destination: "+wsDest);
		
	}
	
	public void chargePackageFromWS() {
		
		ArrayList<Packages> chargeablePackages = new ArrayList<Packages>();
		chargeablePackages = this.wsActual.getPackages(id);
		
		for (int i= 0; i < chargeablePackages.size(); i++) {
			if(this.packages.size() != 0) {
				this.packages.add(chargeablePackages.get(i));
				System.out.println("PACKAGE CHARGED!"+chargeablePackages.get(i).getId());
			}
		}
	}
	
	private void accessWorkstation(Road r) {
		wsDest.addRobotToWorkstation(this);
		System.out.println("ENTERED TO WORKSTATION!");
	}
	
	private void trip() {
		if(!this.access) {
			this.access = wsDest.askForAccess(this);
		}
	}
	
	private void dischargePackage() {
		
		ArrayList<Packages> discharged = new ArrayList<Packages>();
		System.out.println(getNamee()+" DISCHARGING PACKAGE!");
		
		for (int i = 0; i< packages.size(); i++) {
			//if(packages.get(i).getWsDestiny().getId() == getWsActual().getId()) {
				discharged.add(packages.get(i));
			}
		//}
		
		discharged = null;
	}

	@Override
	public void run() {
		
		boolean program = true;
		Packages p = new Packages();
		
		do {
			
			if(packages.size() == 0 || packages == null) {
				
				//chargePackageFromWS();
				chargePackage(p);
				//Movimiento movimiento1=new Movimiento(this, 2, 0);
				
				Road r = new Road(id, wsActual, wsDest);
			
				if(packages.size() > 0) {
					r.askForPermission(this);
					
					waiting();
					
					System.out.println("IN TRIP...");
					
					trip();

					notifyWaitingAccessRobot();
					
					accessWorkstation(r);
					
					dischargePackage();
					
				}
				
				
			}
			
		}while(program);

	}
}

package Robot;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Packages.Packages;
import Packages.Supervisor;
import Workstation.Workstation;

public class Robot extends Thread{
	
	int id;
	String namee;
	boolean on, access;
	
	
	private final Lock lock = new ReentrantLock();
	private final Condition disconnect = lock.newCondition();
	private final Condition waitingAcces = lock.newCondition();
	private final Condition waiting = lock.newCondition();
	
	Workstation wsActual, wsDest;
	public ArrayList<Packages> packages;
	Supervisor supervisor;

	//SQLConnector connection;

	public Robot(int id, String name, boolean on, Workstation wsActual) {
		this.id = id;
		this.namee = name;
		this.on = on;
		this.wsActual = wsActual;
		this.packages = new ArrayList<Packages>();
		this.access = false;
		
		//this.connection = new SQLConnector();

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
	
	public void chargePackage(Packages p) {
		this.packages.add(p);
		System.out.println("Package"+p.getId()+" assigned to "+this.getNamee()+" destination: "+wsDest);
		
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

	@Override
	public void run() {
		
		boolean program = true;
		Packages p = new Packages();
		
		do {
			
			if(packages.size() == 0 || packages == null) {
				
				//chargePackageFromWS();
				chargePackage(p);
				
				waiting();
				
				//waitAccess();
				
				if(packages.size() > 0) {
					notifyWaitingAccessRobot();
				}
				
				
			}
			
		}while(program);

	}


}

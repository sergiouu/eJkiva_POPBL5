package Robot;

import SQLconnection.SQLconnection;

public class Robot extends Thread{
	
	private int id;
	private String namee;
	private boolean status;

	SQLconnection connection;

	public Robot(int id, String name, boolean status) {
		this.id = id;
		this.namee = name;
		this.status = status;
		
		this.connection = new SQLconnection();

	}
	
	
	public long getId() {
		return id;
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


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	@Override
	public void run() {
		this.connection.connect();

	}


}

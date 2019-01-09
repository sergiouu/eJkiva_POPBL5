public class Robot {
	
	Workstation workstationActual,workstationDestino;
	String description;
	public Robot(Workstation workstationActual,Workstation workstationDestino){
		this.workstationActual = workstationActual;
		this.workstationDestino=workstationDestino;
		this.description=null;
	}

	public Workstation getWorkstationActual() {
		return workstationActual;
	}

	public void setWorkstationActual(Workstation workstationActual) {
		this.workstationActual = workstationActual;
	}

	public Workstation getWorkstationDestino() {
		return workstationDestino;
	}

	public void setWorkstationDestino(Workstation workstationDestino) {
		this.workstationDestino = workstationDestino;
	}

	@Override
	public String toString() {
		return "PosX: "+ workstationActual.getPosX() +"\n" +
		        "PosY: " + workstationActual.getPosY() +"\n";
		
	}	
}
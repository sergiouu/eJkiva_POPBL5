import java.util.ArrayList;

public class main {
	static Robot robot;
	Movimiento movimiento;
	Workstation workstationActual,workstationDestino,workstation;
	//Scanner teclado;
	public main(){
		ArrayList<Workstation> workstationList=new ArrayList<>();
		
		workstation=new Workstation(0, 0);
		workstationList.add(workstation);
		workstation=new Workstation(1, 0);
		workstationList.add(workstation);
		workstation=new Workstation(2, 0);
		workstationList.add(workstation);
		workstation=new Workstation(3, 0);
		workstationList.add(workstation);
		workstation=new Workstation(4, 0);
		workstationList.add(workstation);
		workstation=new Workstation(5, 0);
		workstationList.add(workstation);
		workstation=new Workstation(0, 1);
		workstationList.add(workstation);
		workstation=new Workstation(1, 1);
		workstationList.add(workstation);
		workstation=new Workstation(2, 1);
		workstationList.add(workstation);
		workstation=new Workstation(3, 1);
		workstationList.add(workstation);
		workstation=new Workstation(4, 1);
		workstationList.add(workstation);
		workstation=new Workstation(5, 1);
		workstationList.add(workstation);
		
		
		
		workstationActual=new Workstation(2,0);
		workstationDestino=new Workstation(2,1);
		
		robot = new Robot(workstationActual,workstationDestino);
		movimiento=new Movimiento(robot,workstationList);
		movimiento.move();
		//teclado = new Scanner (System.in);
	}

	public static void main(String[] args) {
		main mains =new main();

	}
	}

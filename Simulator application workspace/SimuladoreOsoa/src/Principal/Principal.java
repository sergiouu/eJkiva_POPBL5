package Principal;
import Circuito.Circuito;
import Circuito.Workstation;
import Movimiento.Movimiento;
import Objeto.Robot;
import Producto.AdministradorDeProductos;

public class Principal {
	
	AdministradorDeProductos ap;
	static Robot robot;
	Movimiento movimiento;
	Workstation workstationDestino;
	Circuito circuito;
	
	public Principal(){
		ap = new AdministradorDeProductos();
		circuito = new Circuito(ap);
		ap.setCircuito(circuito);
//		movimiento=new Movimiento(2,2,circuito);
//		movimiento.move();
		
		
	}

	public static void main(String[] args) {
		Principal principal =new Principal();

	}
	}

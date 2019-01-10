package principal;
import circuito.Circuito;
import circuito.Workstation;
import movimiento.Movimiento;
import objeto.Robot;
import producto.AdministradorDeProductos;

public class Principal {
	
	AdministradorDeProductos ap;
	Movimiento movimiento;

	Circuito circuito;
	
	public Principal(){
		circuito = new Circuito();
		ap = new AdministradorDeProductos(circuito);

		ap.start();
		
		
	}

	public static void main(String[] args) {
		Principal principal =new Principal();

	}
	}

package principal;
import circuito.Circuito;
import producto.AdministradorDeProductos;

public class Principal {
	
	AdministradorDeProductos ap;

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

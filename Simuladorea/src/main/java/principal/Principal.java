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
		circuito.setAp(ap);
		
	}

	public static void main(String[] args) {
		Principal principal =new Principal();

	}
	}

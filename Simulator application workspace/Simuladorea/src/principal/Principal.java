package principal;
import circuito.Circuito;
import producto.AdministradorDeProductos;
/**
*
* <h2> Principal class </h2>
* <p>
* Main Class</p>
*
*
*
* @author elarretegui 09/Jan/2019
*/
public class Principal {
	
	AdministradorDeProductos ap;

	Circuito circuito;
	
	/**
	*
	* <h2> Principal </h2>
	* <p>
	* Constructor of principal</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public Principal(){
		circuito = new Circuito();
		ap = new AdministradorDeProductos(circuito);
		ap.start();
		circuito.setAp(ap);
	}
	/**
	*
	* <h2> main </h2>
	* <p>
	* main function of the project</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public static void main(String[] args) {
		Principal principal =new Principal();

	}
	}

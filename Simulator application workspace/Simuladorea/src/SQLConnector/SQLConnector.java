package SQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import circuito.Circuito;
import circuito.Workstation;
import producto.Pedido;
import producto.Producto;
/**
*
* <h2> SQLconnector Class </h2>
* <p>
* Interacts with the database and sends information to the program</p>
*
* @author mbengoa 20/Dec/2018
*
*/
public class SQLConnector {
	//	int lastId=554;
	int lastId;
	
		//SQL conexion initial parameters
		Connection connection = null;
		Statement statement = null;
		String serverName = "ejkiva.duckdns.org:3306";
		String dataBaseName = "mydatabase";
		String url = "jdbc:mysql://";
		String username = "root";
		String password = "eJkiva_POPBL5";
		String connectionString = null;
		Circuito circuito;
		
		/**
		*
		* <h2> SQLConnector </h2>
		* <p>
		* Constructor of SQLConnector class</p>
		*
		*@param circuito
		*
		* @author elarretegui 09/Jan/2019
		*/	
	public SQLConnector(Circuito circuito){
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.circuito = circuito;
			getLastId();
	}
	
	/**
	*
	* <h2> getLastId </h2>
	* <p>
	* Updates the last id</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	private void getLastId() {
		ResultSet rs = null;
		String sqlQuery = "select MAX(orderID) from mydatabase.`order`;";
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			while(rs.next()){
				this.lastId= rs.getInt("orderID");
			}	
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error obteniendo último Id de pedido");
		}
	}
	
	/**
	*
	* <h2> getNuevosPedidos </h2>
	* <p>
	* Gets all the new orders</p>
	*
	*@return List<Pedido>
	*
	* @author elarretegui 09/Jan/2019
	*/
	public List<Pedido> getNuevosPedidos() {
		
		List<Pedido> pedidosNuevos = new ArrayList<Pedido>();
		Pedido p;
		ResultSet rs = null;
		String sqlQuery = "SELECT orderID FROM mydatabase.`order` WHERE orderID > "+lastId+";";
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			
			while(rs.next()){
				p = new Pedido();
				p.setId(rs.getInt("orderID"));
				pedidosNuevos.add(p);
			}
			
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error obteniendo nuevos pedidos");
		}
		
		lastId = lastId + pedidosNuevos.size();
		
		if(pedidosNuevos.size()>0) {
			return pedidosNuevos;
		}
		else return null;
		
	}
	
	/**
	*
	* <h2> getTodosLosProductos </h2>
	* <p>
	* Gets all the products</p>
	*
	*@return List<Producto>
	*
	* @author elarretegui 09/Jan/2019
	*/
	public List<Producto> getTodosLosProductos(){

		List<Producto> listaProductos = new ArrayList<Producto>();
		Producto p;
		ResultSet rs = null;
		String sqlQuery = "SELECT * from mydatabase.product";
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			
			while(rs.next()){
				p = new Producto();
				p.setId(rs.getInt("productID"));
				p.setDescripcion(rs.getString("product_name"));
				p.setWorkstationID(rs.getInt("departamentID"));
				p.setIdPedido(0);
				listaProductos.add(p);
			}
			
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error cargando productos");
		}
		return listaProductos;
	}
	
	/**
	*
	* <h2> insertWorkstations </h2>
	* <p>
	* Inser workstations in the database</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void insertWorkstations() {
		System.out.println("INSERT WORKSTATIONS");
		connect();
		Statement stm = null;
		
		boolean ret = false;
		for(Workstation w : circuito.getWorkstations()) {
		String sqlQuery = "INSERT INTO workstation (workstationID, description, posX, posY, state) "+
		"VALUES("
		+ "'"+w.getId()+"',"		
		+ "'"+w.getDescription()+"',"
		+ "'"+w.getPosX()+"',"
		+ "'"+w.getPosY()+"',"
		+ "'"+w.getInside().getId()+"')";
		
		System.out.println(sqlQuery);
		
		try {
			stm = connection.createStatement();
			if(stm.executeUpdate(sqlQuery)>0) {
				ret=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		}
	}
	
	/**
	*
	* <h2> getProductosDePedido </h2>
	* <p>
	* Get products of the order with id X</p>
	*
	*@param idPedido
	*
	* @author elarretegui 09/Jan/2019
	*/
	 public List<Producto> getProductosDePedido(int idPedido){
		List<Producto> listaProductos = Collections.synchronizedList(new ArrayList<Producto>());
		Producto p;
		ResultSet rs = null;
		String sqlQuery = "select * from mydatabase.orderproduct WHERE orderID = "+idPedido+";";
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			
			while(rs.next()){
				p = new Producto();
				p.setId(rs.getInt("productID"));
				p.setIdPedido(rs.getInt("orderID"));
				p.setCantidad(rs.getInt("quantity"));
				p.setIdPedido(idPedido);			
				listaProductos.add(p);
				}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Error obteniendo nuevos pedidos");
		}
		return listaProductos;	
	}
	
	/**
	*
	* <h2> connect </h2>
	* <p>
	* Used to create a connection with the database</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void connect() {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionString, username, password);
			System.out.println("Connected DB");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("Connection Driver Error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could Not Connect to DB ");
		}
	}

	
	/**
	*
	* <h2> connect </h2>
	* <p>
	* Used to disconnect from the database</p>
	*
	*
	* @author elarretegui 09/Jan/2019
	*/
	public void disconnect() {

		try {
			if (statement != null) {

				statement.clearWarnings();
				statement.close();
			}

			if (connection != null) {

				connection.clearWarnings();
				connection.close();
			}
			
		} catch (SQLException e) {
			System.out.println("Error disconnecting");
		}
	}
	
}

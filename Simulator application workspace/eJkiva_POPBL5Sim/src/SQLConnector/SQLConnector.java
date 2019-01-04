package SQLConnector;

import java.sql.*;

public class SQLConnector {

		//SQL conexion initial parameters
		Connection connection = null;
		Statement statement = null;
		String serverName = "ejkiva.duckdns.org";
		String dataBaseName = "mydatabase";
		String url = "jdbc:mysql://";
		String username = "root";
		String password = "eJkiva_POPBL5";
		String connectionString = null;

	
	public SQLConnector(){
		this.connectionString = url + serverName + "/" + dataBaseName;

	}
	
	/**
	 * Used to create a connection with the database
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
	 * Method used to disconnect from the database
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

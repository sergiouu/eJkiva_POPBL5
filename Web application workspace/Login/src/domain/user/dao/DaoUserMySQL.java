package domain.user.dao;

import java.util.ArrayList;

import domain.user.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUserMySQL implements DaoUser {

	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "login";
	String url = "jdbc:mysql://";
	String username = "root";
	String password = "";
	String connectionString = null;

	public DaoUserMySQL() {
		this.connectionString = url + serverName + "/" + dataBaseName;
	}
	

	private void connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionString,
					username, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {

			System.out.println("Connection Driver Error");
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Could Not Connect to DB ");
		}
	}

	private void disconnect() {

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

	@Override
	public User loadUser(String username, String password) {
		User user = null;
		connect();
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM user "
				+ "WHERE username='"+username+"' "
				+ "AND password='"+password+"';";
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			if(rs.next()){
				user = new User();
				
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setSecondName(rs.getString("second_name"));
				user.setEmail(rs.getString("email"));
			}
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error DaoLoginMysql exists select");
		}
		disconnect();
		return user;
	}

	@Override
	public User loadUser(int userId) {

		User user = null;
		connect();
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM user "
				+ "WHERE userId="+userId;
		System.out.println(sqlQuery);
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			if(rs.next()){
				user = new User();
				
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setSecondName(rs.getString("second_name"));
				user.setEmail(rs.getString("email"));
			}
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error DaoLoginMysql exists select");
		}
		disconnect();
		return user;
	}

	@Override
	public ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		connect();
		ResultSet rs = null;
		String sqlQuery = "SELECT * FROM user";
		System.out.println(sqlQuery);
		
		try{
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sqlQuery);
			while(rs.next()){
				user = new User();
				
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setSecondName(rs.getString("second_name"));
				user.setEmail(rs.getString("email"));
				
				users.add(user);
			}
		}catch(SQLException e){
			System.out.println(sqlQuery);
			e.printStackTrace();
			System.out.println("Error DaoUserMySQL loadUsers select");
		}
		disconnect();
		return users;
	}

	@Override
	public boolean insertUser(User user) {
		boolean ret = false;
		String sqlQuery = "INSERT INTO user (username,password,first_name,second_name,email) "+
		"VALUES("
		+ "'"+user.getUsername()+"',"
		+ "'"+user.getPassword()+"',"
		+ "'"+user.getFirstName()+"',"
		+ "'"+user.getSecondName()+"',"
		+ "'"+user.getEmail()+"'"
		+ ")";
		System.out.println(sqlQuery);
		
		connect();
		try{
			Statement stm = connection.createStatement();
			if(stm.executeUpdate(sqlQuery)>0){
				ret=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		disconnect();
		return ret;
	}
	
	@Override
	public boolean updateUser(User user) {
		boolean ret = false;
		String sqlQuery = "UPDATE user "+
		"SET "
		+ "username='"+user.getUsername()+"',"
		+ "password='"+user.getPassword()+"',"
		+ "first_name='"+user.getFirstName()+"',"
		+ "second_name='"+user.getSecondName()+"',"
		+ "email='"+user.getEmail()+"'"
		+ "WHERE userId="+user.getUserId();
		System.out.println(sqlQuery);
		connect();
		try{
			Statement stm = connection.createStatement();
			if(stm.executeUpdate(sqlQuery)>0){
				ret=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		disconnect();
		return ret;
		
	}


	@Override
	public boolean deleteUser(int userId) {

		boolean ret = false;
		String sqlQuery = "DELETE FROM user WHERE userId="+userId;
		System.out.println(sqlQuery);
		connect();
		try{
			Statement stm = connection.createStatement();
			if(stm.executeUpdate(sqlQuery)>0){
				ret=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		disconnect();
		return ret;
	}

}

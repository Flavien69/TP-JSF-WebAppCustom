package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.UserModelBean;

public class UserDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	private String dB_USERTABLE = "users";
	
	public UserDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addUser(UserModelBean user) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			String sql = "INSERT INTO `"+ dB_NAME +"`.`"+dB_USERTABLE+"` (`email`, `firstname`, `lastname`, `age`, `login`, `pwd`,`admin`) VALUES ('"
					+ user.getEmail()
					+ "', '"
					+ user.getFirstname()
					+ "', '"
					+ user.getLastname()
					+ "', '"
					+ user.getAge()
					+ "', '"
					+ user.getLogin()
					+ "', '"
					+ user.getPwd()
					+ "', "
					+ user.isAdmin()
					+ ");";
			
			
			int rs = query.executeUpdate(sql);
			
			
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(UserModelBean user) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			String sql = "UPDATE  `"+ dB_NAME +"`.`"+dB_USERTABLE+"` SET `firstname`='"+ user.getFirstname()
					+"',`lastname`='"+ user.getLastname()
					+"',`age`="+ user.getAge()
					+",`email`='"+ user.getEmail()
					+"',`login`='"+ user.getLogin()
					+"',`pwd`='"+ user.getPwd()
					+"',`admin`="+ user.isAdmin()
					+" WHERE `users`.`email` = '"+user.getEmail()+"'";
			
			
			int rs = query.executeUpdate(sql);
			
			
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(UserModelBean user) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			String sql = "DELETE FROM`"+ dB_NAME +"`.`"+dB_USERTABLE+"` WHERE `users`.`email` = '"+user.getEmail()+"'";
			
			int rs = query.executeUpdate(sql);
			
			
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserModelBean> getAllUser() {
		// return value
		ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Cr�ation de la requ�te
			java.sql.Statement query;

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+dB_USERTABLE);
			while (rs.next()) {
				// Cr�ation de l'utilisateur
				UserModelBean user = new UserModelBean(
						rs.getString("firstname"), rs.getString("lastname"),
						rs.getInt("age"), rs.getString("login"),
						rs.getString("pwd"),rs.getString("email"),rs.getBoolean("admin"));
				System.out.println("User : " + user);

				// ajout de l'utilisateur r�cup�r� � la liste
				userList.add(user);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserModelBean checkUser(String login, String pwd) {
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Cr�ation de la requ�te
			java.sql.Statement query;

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+dB_USERTABLE+" where login='"
							+ login + "' and pwd='" + pwd + "';");

			if (!rs.next()) {
				return null;
			} else {
				// Cr�ation de l'utilisateur
				UserModelBean user = new UserModelBean(					
				rs.getString("firstname"),rs.getString("lastname"), 
				rs.getInt("age"), rs.getString("login"),
				rs.getString("pwd"),rs.getString("email"),rs.getBoolean("admin"));
				System.out.println("User Login : " + user);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}

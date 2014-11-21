//package step2.db;
//
//import java.sql.SQLException;
//import java.sql.Connection;
//import step2.model.UserModelBean;
//
//public class DB {
//	private static final String DB_HOST = "http://localhost/";
//	private static final String DB_PORT = "3000";
//	private static final String DB_NAME = "stepByStep";
//	private static final String DB_USER = "client";
//	private static final String DB_PWD = "client";
//	private static final String DB_TABLE_USER = "users";
//
//	private static final String LASTNAME = "lastname";
//	private static final String SURNAME = "surname";
//	private static final String AGE = "age";
//	private static final String LOGIN = "login";
//	private static final String PWD = "pwd";
//	private Connection connection;
//	
//	public DB() {
//		openConnection();
//	}
//
//	private void openConnection() {
//		try {
//			// Chargement du Driver, puis établissement de la connexion
//			Class.forName("com.mysql.jdbc.Driver");
//			// create connection
//			// connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
//			// + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PWD);
//
//			connection = java.sql.DriverManager
//					.getConnection("jdbc:mysql://localhost/" + DB_NAME + "?"
//							+ "user=" + DB_USER + "&password=" + DB_PWD);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void addUser(UserModelBean user) {
//		// Création de la requête
//		java.sql.Statement stmt;
//
//		try {
//
//			String querry = "INSERT INTO " + DB_TABLE_USER + " (" + SURNAME
//					+ "," + LASTNAME + ", " + AGE + ", " + LOGIN + ", " + PWD
//					+ ") VALUES ('" + user.getSurname() + "','"
//					+ user.getLastname() + "','" + user.getAge() + "','"
//					+ user.getLogin() + "','" + user.getPwd() + "');";
//
//			openConnection();
//			stmt = connection.createStatement();
//			stmt.executeUpdate(querry);
//
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//
//}

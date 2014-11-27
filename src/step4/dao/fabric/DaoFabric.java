package step4.dao.fabric;

import step4.dao.instance.RecipesDao;
import step4.dao.instance.UserDao;

public final class DaoFabric {

	// L'utilisation du mot cl� volatile permet, en Java version 5 et sup�rieur,
	// permet d'�viter le cas o� "Singleton.instance" est non-nul,
	// mais pas encore "r�ellement" instanci�.
	// De Java version 1.2 � 1.4, il est possible d'utiliser la classe
	// ThreadLocal.
	private static volatile DaoFabric instance = null;


	private static final String DB_HOST = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "cookMe";
	private static final String DB_USER = "client";
	private static final String DB_PWD = "client";

	private DaoFabric() {
		super();
		try {
			// Chargement du Driver, puis �tablissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�thode permettant de renvoyer une instance de la classe Singleton
	 * 
	 * @return Retourne l'instance du singleton.
	 */
	public final static DaoFabric getInstance() {

		if (DaoFabric.instance == null) {

			synchronized (DaoFabric.class) {
				if (DaoFabric.instance == null) {
					DaoFabric.instance = new DaoFabric();
				}
			}
		}
		return DaoFabric.instance;
	}

	public UserDao createUserDao() {
		UserDao userDao = new UserDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
		return userDao;
	}
	
	public RecipesDao createRecipesDao(){
		RecipesDao receipesDao = new RecipesDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
		return receipesDao;
	}

}

package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.RecipeModelBean;

public class RecipesDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;
	private String DB_RECIPESTABLE = "recipes";

	public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addRecipe(RecipeModelBean recipe) {
		java.sql.Statement query;
		
		// create connection
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			query = connection.createStatement();

			String sql = "INSERT INTO `"+ dB_NAME +"`.`"+DB_RECIPESTABLE+"` (`title`, `description`, `expertise`, `duration`, `nbpeople`,`type`) VALUES ('"
					+ recipe.getTitle()
					+ "', '"
					+ recipe.getDescription()
					+ "', '"
					+ recipe.getExpertise()
					+ "', '"
					+ recipe.getDuration()
					+ "', '"
					+ recipe.getNbpeople()
					+ "', '" + recipe.getType() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<RecipeModelBean> getAllRecipes() {
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();

		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();

			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+DB_RECIPESTABLE+";");
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModelBean recipe = new RecipeModelBean(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				// ajout de la recette r�cup�r�e � la liste
				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}

	public ArrayList<RecipeModelBean> getRecipesWithFilters(int duration, int expertise, int nbpeople, String type) {
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();

		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			query = connection.createStatement();

			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM "+DB_RECIPESTABLE+" "
							+ "WHERE 'duration'="+duration+" AND 'expertise'="+expertise+""
							+ " AND 'nbpeople'="+nbpeople+" AND 'type'='"+type+"';");
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModelBean recipe = new RecipeModelBean(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}
}

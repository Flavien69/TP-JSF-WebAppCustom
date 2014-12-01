package step4.processing;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;
import step4.model.RecipeListModelBean;
import step4.model.RecipeModelBean;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {
	private RecipesDao recipeDao;
	
	public RecipeControlerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}
	
	
	public void loadAllRecipe(){
//		ArrayList<RecipeModelBean> list = this.recipeDao.getAllRecipes();
//		
//		RecipeListModelBean recipeList=new RecipeListModelBean();
//		
//		for(RecipeModelBean recipe:list){
//			//recipeList.addRecipeList(recipe);
//		}
		

		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		//place la liste de recette dans l'espace de m�moire de JSF
		//sessionMap.put("recipeList", recipeList);

		
	}
	
	
	public void getRecipeById(RecipeModelBean recipeFromView){
		
		RecipeModelBean recipeDetail = recipeFromView;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.put("recipeDetail", recipeDetail);
		
		try {
			externalContext.redirect("recipeDetail.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchRecipes(RecipeModelBean recipe){

		List<RecipeModelBean> recipes = recipeDao.getRecipesWithFilters(recipe.getDuration(), recipe.getExpertise(), recipe.getNbpeople(), recipe.getType());
		RecipeListModelBean recipeListBean = new RecipeListModelBean(recipes);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.put("r", recipeListBean.getRecipeList());

		
		try {
			externalContext.redirect("recipesResult.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

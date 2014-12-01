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
import utils.GrowlView;

@ManagedBean
@ApplicationScoped
public class RecipeControlerBean {
	private RecipesDao recipeDao;
	private GrowlView gv;
	
	public RecipeControlerBean() {
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
		gv = new GrowlView();
	}
	
	
	public void loadAllRecipe(){
		List<RecipeModelBean> list = this.recipeDao.getAllRecipes();
		
		RecipeListModelBean recipeList=new RecipeListModelBean();
		
		for(RecipeModelBean recipe:list){
			recipeList.addRecipeList(recipe);
		}
		

		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		//place la liste de recette dans l'espace de m�moire de JSF
		sessionMap.put("recipeList", recipeList.getRecipeList());

		
	}

	public void searchRecipes(RecipeModelBean recipe){
		int duration = recipe.getDuration();
		int people = recipe.getNbpeople();
		String type = recipe.getType();
		List<RecipeModelBean> recipes = recipeDao.getRecipesWithFilters(recipe.getDuration(), recipe.getExpertise(), recipe.getNbpeople(), recipe.getType());
		RecipeListModelBean recipeListBean = new RecipeListModelBean(recipes);
		System.out.println("coucou");
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.remove("r");
		if(recipeListBean.getRecipeList().size() != 0)
			sessionMap.put("r", recipeListBean.getRecipeList());
		else
			sessionMap.put("r", recipeListBean.getRecipeList());
		
		try {
			externalContext.redirect("recipesResult.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initAddingRecipePanel(){
		
		//V�rifier les propri�t�s de l'utilisateur
		 
		RecipeModelBean usmb = new RecipeModelBean();
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.remove("selectedRecipe");
		sessionMap.put("selectedRecipe", usmb);
		
		sessionMap.remove("pendingActionRecipe");
		sessionMap.put("pendingActionRecipe", "adding");
		//ajout de l'utilisateur � la base de donn�es
		
	}
	
	public void checkAndAddRecipe(RecipeModelBean recipeSubmitted ){
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		this.recipeDao.addRecipe(recipeSubmitted);
		
		sessionMap.remove("pendingActionRecipe");
		sessionMap.put("pendingActionRecipe", null);
		
		gv.setTitle("added with success");
		gv.saveInfoMessage();
		
	}
	
	public void checkAndUpdateRecipe(RecipeModelBean recipeSubmitted  ){
		
		//V�rifier les propri�t�s de l'utilisateur
		 
		this.recipeDao.updateRecipe(recipeSubmitted);

		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.remove("pendingActionRecipe");
		sessionMap.put("pendingActionRecipe", null);
		
	}
	
	public void setSelectedRecipe(RecipeModelBean recipe){
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		//place l'utilisateur dans l'espace de m�moire de JSF
		sessionMap.remove("selectedRecipe");
		sessionMap.put("selectedRecipe", recipe);
		
		sessionMap.remove("pendingActionRecipe");
		sessionMap.put("pendingActionRecipe", "update");
	}
	
	public void deleteRecipe(RecipeModelBean recipe){
		this.recipeDao.deleteUser(recipe);	
		gv.setTitle("delte successful");
		gv.saveInfoMessage();
	}
}

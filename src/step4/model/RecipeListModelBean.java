package step4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RecipeListModelBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();
	
	public RecipeListModelBean() {
	}
	
	public RecipeListModelBean(List<RecipeModelBean> recipes) {
		recipeList = recipes;
	}
	
	public void addRecipeList(RecipeModelBean recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModelBean> getRecipeList() {
		return recipeList;
	}

}

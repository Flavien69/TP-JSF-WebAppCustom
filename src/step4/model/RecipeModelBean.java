package step4.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@RequestScoped
public class RecipeModelBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private int expertise;
	private int nbpeople;
	private int duration;
	private String type;
	private String img;
	private  int id;
	
	public RecipeModelBean() {
	}
	
	
	public RecipeModelBean(String title,String description,int expertise,int duration,int nbpeople,String type, String img, int id) {
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.duration = duration;
		this.nbpeople = nbpeople;
		this.type = type;
		this.img = img;
		this.id = id;
	}
	
	
	
	
	
	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExpertise() {
		return expertise;
	}
	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}
	public int getNbpeople() {
		return nbpeople;
	}
	public void setNbpeople(int nbpeople) {
		this.nbpeople = nbpeople;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}

package step4.processing;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.UserDao;
import step4.model.LoginBean;
import step4.model.UserModelBean;
import step4.model.UserSubmissionModelBean;
import utils.GrowlView;

@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entr� unique � l'ensemble des clients
public class UserControlerBean {
	private UserDao userDao;
	private GrowlView gv;
	
	public UserControlerBean() {
		this.userDao=DaoFabric.getInstance().createUserDao();
		gv = new GrowlView();
	}
	
	public void checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		if( user!=null){
			
			//r�cup�re l'espace de m�moire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de m�moire de JSF
			sessionMap.put("loggedUser", user);
			
			gv.setTitle("Connection success");
			gv.setMessage("Log in successful");
			gv.saveInfoMessage();
		}
		else{
			gv.setTitle("Connection error");
			gv.setMessage("Log in fail");
			gv.saveErrorMessage();
		}
			
	}
	
	public void checkAdminUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		if( user!=null){
			if(user.isAdmin()){
				//r�cup�re l'espace de m�moire de JSF
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				
				//place l'utilisateur dans l'espace de m�moire de JSF
				sessionMap.put("loggedAdminUser", user);
				sessionMap.put("loggedUser", user);
				
				gv.setTitle("Connection success");
				gv.setMessage("Log in successful");
				gv.saveInfoMessage();
			}else{
				gv.setTitle("Permission denied");
				gv.setMessage("Log in fail");
				gv.saveErrorMessage();
			}
		}
		else{
			gv.setTitle("Connection error");
			gv.setMessage("Log in fail");
			gv.saveErrorMessage();
		}
			
	}
	public void logOutUser(){
			
			try			{		
			//r�cup�re l'espace de m�moire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de m�moire de JSF
			sessionMap.put("loggedUser", null);
			sessionMap.put("loggedAdminUser", null);
			
			gv.setTitle("Connection success");
			gv.setMessage("Log out successful");	
			gv.saveInfoMessage();
			
			}catch(Exception e){
				gv.setTitle("Connection error");
				gv.setMessage("Log out fail");
				gv.saveErrorMessage();
			}
			

	}
	
	public void getUsers(){
		ArrayList<UserModelBean> users = this.userDao.getAllUser();
		if( users!=null){
			
			//r�cup�re l'espace de m�moire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de m�moire de JSF
			sessionMap.put("users", users);
		}
	
	}
	
	public void setSelectedUser(UserModelBean user){
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		UserSubmissionModelBean usmb = new UserSubmissionModelBean(user.getFirstname(),user.getLastname(),user.getAge(),user.getLogin(),user.getPwd(),user.getEmail(),user.getPwd(),user.isAdmin());

		
		//place l'utilisateur dans l'espace de m�moire de JSF
		sessionMap.remove("selectedUser");
		sessionMap.put("selectedUser", usmb);
		
		sessionMap.remove("pendingAction");
		sessionMap.put("pendingAction", "update");
	}
	
	public void deleteUser(UserModelBean user){
		this.userDao.deleteUser(user);	
	}
	
	public void checkAndAddUser(UserSubmissionModelBean userSubmitted ){
		
		//V�rifier les propri�t�s de l'utilisateur
		 
		if (userSubmitted.getPwd().equals(userSubmitted.getRepeatPwd()))
		{
			this.userDao.addUser(userSubmitted);
		}
		//ajout de l'utilisateur � la base de donn�es
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.remove("pendingAction");
		sessionMap.put("pendingAction", null);
		
	}
	public void checkAndUpdateUser(UserSubmissionModelBean userSubmitted ){
		
		//V�rifier les propri�t�s de l'utilisateur
		 
		if (userSubmitted.getPwd().equals(userSubmitted.getRepeatPwd()))
		{
			this.userDao.updateUser(userSubmitted);
		}
		
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.remove("pendingAction");
		sessionMap.put("pendingAction", null);
		//ajout de l'utilisateur � la base de donn�es
		
	}
	
public void initAddingUserPanel(){
		
		//V�rifier les propri�t�s de l'utilisateur
		 
		UserSubmissionModelBean usmb = new UserSubmissionModelBean("first name","last name",1,"login","pwd","exemple@gmail.com","pwd",false);
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.remove("selectedUser");
		sessionMap.put("selectedUser", usmb);
		
		sessionMap.remove("pendingAction");
		sessionMap.put("pendingAction", "adding");
		//ajout de l'utilisateur � la base de donn�es
		
	}

}

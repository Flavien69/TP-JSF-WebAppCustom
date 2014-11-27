package step4.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable{
	private String firstname;
	private String lastname;
	private int age;
	private String login;
	private String email;
	private String pwd;
	private boolean admin = false;
	//Contrainte BEAN constructeur sans param�tre
	public UserModelBean() {
	}
	
	public UserModelBean(String firstname, String lastname ,int age,String login,String pwd,String email,boolean admin) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.login = login;
		this.pwd = pwd;
		this.email = email;
		this.admin = admin;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	@Override
	public String toString() {
		return "[LASTNAME]:"+this.getLastname()+",[AGE]:"+this.getAge()+",[LOGIN]:"+this.getLogin()+",[PWD]:"+this.getPwd()+",[EMAIL]:"+this.getEmail();
	}
	

}

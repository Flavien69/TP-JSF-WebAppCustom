package step4.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped 
public class UserSubmissionModelBean extends UserModelBean{

	private static final long serialVersionUID = 1L;
	
	private String lastname;
	private String surname;
	private int age;
	private String login;
	private String pwd;
	private String repeatPwd;

	public UserSubmissionModelBean() {
	}

	public UserSubmissionModelBean(String lastname, String surname, int age,
			String login, String pwd, String repeatPwd) {
		super();
		this.lastname = lastname;
		this.surname = surname;
		this.age = age;
		this.login = login;
		this.pwd = pwd;
		this.repeatPwd = repeatPwd;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getRepeatPwd() {
		return repeatPwd;
	}

	public void setRepeatPwd(String repeatPwd) {
		this.repeatPwd = repeatPwd;
	}
}

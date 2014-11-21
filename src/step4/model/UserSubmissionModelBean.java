package step4.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped 
public class UserSubmissionModelBean extends UserModelBean{

	private static final long serialVersionUID = 1L;

	private String repeatPwd;

	public UserSubmissionModelBean() {
	}

	public UserSubmissionModelBean(String firstname, String lastname, String surname, int age,
			String login, String pwd, String email, String repeatPwd) {
		super( firstname, lastname, surname, age, login, pwd, email);
		this.repeatPwd = repeatPwd;
	}

	public UserSubmissionModelBean( String repeatPwd) {
		super();
		this.repeatPwd = repeatPwd;
	}

	public String getRepeatPwd() {
		return repeatPwd;
	}

	public void setRepeatPwd(String repeatPwd) {
		this.repeatPwd = repeatPwd;
	}

}


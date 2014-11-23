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

	public UserSubmissionModelBean(String firstname, String lastname,  int age,
			String login, String pwd, String email, String repeatPwd, boolean admin) {
		super( firstname, lastname, age, login, pwd, email, admin);
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


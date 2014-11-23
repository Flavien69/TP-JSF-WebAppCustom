package step4.model;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class UsersModelBean implements Serializable {
     
    private List<UserModelBean> userModelBean;

	public List<UserModelBean> getUserModelBean() {
		return userModelBean;
	}

	public void setUserModelBean(List<UserModelBean> userModelBean) {
		this.userModelBean = userModelBean;
	}

	public UsersModelBean() {
		super();
	}

	public UsersModelBean(List<UserModelBean> userModelBean) {
		super();
		this.userModelBean = userModelBean;
	}

}
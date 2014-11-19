package validators;

import java.util.regex.Matcher;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.password")
public class UserPasswordValidator implements Validator {

 
	private Matcher matcher;
 
	public UserPasswordValidator() {}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		UIInput passwordComponent = (UIInput) component.getAttributes().get("passwordComponent");
        String password = (String) passwordComponent.getValue();
        String confirmPassword = (String) value;

        if (confirmPassword != null && !confirmPassword.equals(password)) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Confirm password is not the same as password", null));
        }
 
		}
 
	}

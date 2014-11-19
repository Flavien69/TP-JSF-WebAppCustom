package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.lastname")
public class UserNameValidator implements Validator {

	private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-@]+" ;
 
	private Pattern pattern;
	private Matcher matcher;
 
	public UserNameValidator() {
		 pattern = Pattern.compile(USERNAME_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("user name validation failed.", 
						"Validation failed please follow the contraint "+USERNAME_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}

}

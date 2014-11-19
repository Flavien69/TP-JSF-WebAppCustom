package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.age")
public class UserAgeValidator implements Validator {

	private static final String USERAGE_PATTERN = "^[0-9]+" ;
 
	private Pattern pattern;
	private Matcher matcher;
 
	public UserAgeValidator() {
		 pattern = Pattern.compile(USERAGE_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if(!matcher.matches() ){
 
			FacesMessage msg = 
				new FacesMessage("user age validation failed.", 
						"age must be a number");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
		else{
			try{
			  int num = Integer.parseInt(value.toString());
			  if (num > 100 ){
					 
					FacesMessage msg = 
						new FacesMessage("user age validation failed.", 
								"age must be <100");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
		 
				}
			} catch (NumberFormatException e) {
				FacesMessage msg = 
						new FacesMessage("user age validation failed.", 
								"age must be a number");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
			}
		}
		
		
 
	}

}

package at.kfiw.valley3.validator;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



@ManagedBean
@RequestScoped
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator
{
	FacesMessage message;
	

	@Override
	public void validate(FacesContext context, UIComponent componentToValidate,
			Object value)
	{
		String password2 = String.valueOf(value);
		UIInput passwordField = (UIInput) context.getViewRoot().findComponent("registry:pw");
		
		String password = (String) passwordField.getValue();

		if (!password2.equals(password))
		{
			message = new FacesMessage("Passwörter stimmen nicht überein");
			throw new ValidatorException(message);
		}
	}
}

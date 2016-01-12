package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
@FacesValidator(value="registryValidator")
public class RegistryValidator implements Validator
{
	
	
	FacesMessage message;
	@EJB
	Service service;
	
		
	@Override
	public void validate(FacesContext context,
			UIComponent componentToValidate, Object value)
	{
		String email = String.valueOf(value);
		
		boolean e = false;
		try
		{
			// boolean
			e = service.getOrganizerByEmail(email);
					
		} catch (Exception ex)
		{
			message = new FacesMessage(
					"Fehler beim Vergleichen der email-Adressen");
			throw new ValidatorException(message);
		}

		if (e == false)
		{
			message = new FacesMessage(
					"Eine Registrierung mit dieser email existiert bereits");
			throw new ValidatorException(message);
		}

	}

	
}

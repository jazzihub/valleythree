package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class LoginValidator
{
	FacesMessage message;
	
	@EJB
	Service service;
	
	private static final Logger logger = LoggerFactory.getLogger(Service.class);
	
	public LoginValidator()
	{

	}
	
	public void validOrganizerEmail(FacesContext context,
			UIComponent componentToValidate, Object value)
	{

		Organizer o = (Organizer) FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("organizer");

		boolean email = true;
		try
		{
			//liefert false, bei gültiger Email
			email = service.getOrganizerByEmail(o.getEmail());
					
		} catch (Exception e)
		{
			message = new FacesMessage(
					"Fehler beim Vergleichen der email-Adressen");
			throw new ValidatorException(message);
		}

		if (email == true)
		{
			message = new FacesMessage(
					"ungueltige E-Mail-Adresse");
			throw new ValidatorException(message);
		}
	}
	
//	public void validOrganizerPassword(FacesContext context,
//			UIComponent componentToValidate, Object value)
//	{
//		Organizer o = (Organizer) FacesContext.getCurrentInstance().getExternalContext()
//				.getSessionMap().get("organizer");
//
//		boolean password = false;
//		try
//		{
//			//liefert true bei gefundenem Passwort
//			password = service.getExistingPassword(o.getPassword());
//					
//		} catch (Exception e)
//		{
//			message = new FacesMessage(
//					"Fehler beim Vergleichen der Passwörter");
//			throw new ValidatorException(message);
//		}
//
//		if (password == false)
//		{
//			message = new FacesMessage(
//					"ungueltiges Passwort");
//			throw new ValidatorException(message);
//		}
//	}
	
	public String validLogin()
	{
		Organizer o = (Organizer) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("organizer");
		boolean validLogin = false;
		try
		{
			validLogin = service.getOrganizerByEmailAndPassword(o.getEmail(),o.getPassword());
			System.out.println("OrganizerController.addOrganizer ok");
		} catch (Throwable t)
		{
			logger.error("Fehler OrganizerController: Veranstalter konnte nicht hinzugefügt werden");
		}
		
		if(validLogin == true)
		{
			return "okidoki";
		}
		else
			return "nono";
		
	}
}


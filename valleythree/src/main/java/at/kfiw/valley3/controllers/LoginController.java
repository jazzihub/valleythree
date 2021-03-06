package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.services.Service;

@ManagedBean
@SessionScoped
public class LoginController
{
	FacesMessage message;

	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	boolean validLogin;	

	public LoginController() {}

	public String validLogin()
	{
		Organizer o = (Organizer) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("organizer");
		
		validLogin = false;
		
		try
		{
			EncodingPassword pw = new EncodingPassword();
			String securePassword = pw.encrypt(o.getPassword());
			validLogin = service.getOrganizerByEmailAndPassword(o.getEmail(),
					securePassword);
			logger.info("LoginController.validLogin() ok");
		} catch (Exception e)
		{
			logger.error("LoginController.validLogin(): Fehler beim Login");
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Fehler beim �berpr�fen der Daten!",
							"Versuchen Sie es nocheinmal!"));
		}

		if (validLogin == true)
		{
			try
			{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext
						.getExternalContext().getSession(true);
				//Sessionattribut setzen
				session.setAttribute("currentUser", o);
				logger.info("Sessionattribut hinzugef�gt");
				return "index.xhtml";
			} catch (Exception e)
			{				
				FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Beim Login ist ein Fehler aufgetreten",
								"Versuchen Sie es nocheinmal!"));
				return null;
			}
		} else
		{
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Ung�ltiger Login!",
									"Versuchen Sie es nocheinmal!"));
			
			return null; // null, wenn man auf gleicher Seite bleiben soll
		}

	}

	public String logout()
	{
		try
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpSession session = (HttpSession) facesContext.getExternalContext()
					.getSession(true);
			session.removeAttribute("currentUser");
			session.invalidate();
			validLogin = false;
			logger.info("logout ok, Session invalidate");		
			return null;
		} catch (Exception e)
		{
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Beim Ausloggen ist ein Fehler aufgetreten",
							""));
			return null;
		}
	}

	// Getter Setter
	public boolean getValidLogin()
	{
		return validLogin;
	}

	public void setValidLogin(boolean validLogin)
	{
		this.validLogin = validLogin;
	}

}

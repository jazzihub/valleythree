package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class LoginController
{
	FacesMessage message;

	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	public LoginController()
	{

	}

	public String validLogin()
	{
		Organizer o = (Organizer) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("organizer");
		boolean validLogin = false;
		try
		{
			validLogin = service.getOrganizerByEmailAndPassword(o.getEmail(),
					o.getPassword());
			logger.info("LoginController.validLogin() ok");
		} catch (Throwable t)
		{
			logger.error("LoginController.validLogin(): Fehler beim Login");
		}

		if (validLogin == true)
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext
					.getExternalContext().getSession(true);
			session.setAttribute("email", o.getEmail());
			session.setAttribute("email", o.getEmail());
			logger.info("Sessionattribut hinzugefügt");
			return "index";
		} else
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Login!", "Please Try Again!"));

			// invalidate session, and redirect to other pages
			return "login";
		}

	}
}

// public String loginProject() {
// boolean result = UserDAO.login(uname, password);
// if (result) {
// // get Http Session and store username
// HttpSession session = Util.getSession();
// session.setAttribute("username", uname);
//
// return "home";
// } else {
//
// FacesContext.getCurrentInstance().addMessage(
// null,
// new FacesMessage(FacesMessage.SEVERITY_WARN,
// "Invalid Login!",
// "Please Try Again!"));
//
// // invalidate session, and redirect to other pages
//
// //message = "Invalid Login. Please Try Again!";
// return "login";
// }
// }
//
// public String logout() {
// HttpSession session = Util.getSession();
// session.invalidate();
// return "login";
// }


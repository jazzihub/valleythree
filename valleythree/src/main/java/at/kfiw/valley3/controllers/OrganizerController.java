package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.services.Service;

@ManagedBean
@SessionScoped
public class OrganizerController
{
	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);


	public void addOrganizer()
	{
		Organizer o = (Organizer) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("organizer");
		
		Place p = (Place) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("place");

		try
		{
			service.addPlace(p);
			
			service.addOrganizer(o);
			System.out.println("OrganizerController.addOrganizer ok");
		} catch (Throwable t)
		{
			logger.error("Fehler OrganizerController: Veranstalter konnte nicht hinzugefügt werden");
		}

	}
}

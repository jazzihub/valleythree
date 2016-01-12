package at.kfiw.valley3.controllers;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class EventController
{
	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);


	public void addEvent()
	{
		Event e = (Event) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("event");

		try
		{
			service.addEvent(e);
			System.out.println("EventController.addEvent ok");
		} catch (Throwable t)
		{
			logger.error("Fehler EventController: Event konnte nicht hinzugefügt werden");
		}

	}

	public List<Event> getEvents()
	{

		try
		{
			System.out.println("EventController.getEvents ok");
			return service.getAllEvents();

		} catch (Throwable t)
		{
			logger.error("Fehler Controller: Event konnte nicht hinzugefügt werden");
		}

		return null;

	}
	
	public List<Event> getEventsFromNow()
	{

		try
		{
			System.out.println("EventController.getEventsFromNow ok");
			return service.getEventFromNow();

		} catch (Throwable t)
		{
			logger.error("Fehler Controller: Event konnte nicht hinzugefügt werden");
		}

		return null;

	}

	

}

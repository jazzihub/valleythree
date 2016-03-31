package at.kfiw.valley3.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Location;
import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped

public class EventController implements Serializable
{
	
	private static final long serialVersionUID = 8812813254216636592L;

	@EJB
	Service service;
	
	@ManagedProperty(value="#{event}")
	private Event e;
	
	@ManagedProperty(value="#{location}")
	private Location l;
	
	@ManagedProperty(value="#{place}") 
	private Place p;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	public String addEvent()
	{

		Place existingPlace = null;
		
		Location existingLocation = null;

		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = req.getSession(true);		
		
		Organizer tempOrganizer = (Organizer) session.getAttribute("currentUser");
		Organizer o = service.getOrganizerByEmail(tempOrganizer.getEmail());
		
//		Event e = (Event) FacesContext.getCurrentInstance()
//				.getExternalContext().getSessionMap().get("event");

		try
		{
			existingPlace = service.getPlaceByPlz(p.getPlz());
			existingLocation = service.getLocationByNameAndPlz(l.getName(), p.getPlz());
			
			//service.addEvent(existingPlace, existingLocation, e, p, l, o);
			// wenn PLZ bereits in DB in vorhanden, dann neuen Place hinzufügen
			if (existingPlace == null)
			{

				//p.addLocation(l);
				l.setPlace(p);
				service.addPlace(p);
			} else
			{
				l.setPlace(existingPlace);
			}
			
			if (existingLocation == null)
			{
				//l.addEvent(e);
				e.setLocation(l);
				service.addLocation(l);
			}
			else
			{
				e.setLocation(existingLocation);
			}
			
			e.setOrganizer(o);
			service.addEvent(e);
			logger.info("EventController.addEvent ok");
			return "organizerArea";
			
		} catch (Throwable t)
		{
			logger.error("Fehler EventController.addEvent(): Event konnte nicht hinzugef�gt werden",t);
		}
		finally
		{
			clear();
		}
		
		return null;

	}
	
	public void clear()
	{
		e.setName(null);
		e.setBegin(null);
		e.setTime(null);
		e.setKind(null);
		e.setDetail(null);
		e.setDescription(null);
		l.setStreet(null);
		p.setPlz((short)0);
		p.setPlace(null);
		l.setName(null);
		e.setTicketsTotal((short)0);
		e.setTicketInfo(null);
		e.setContributor(null);
		
	}
	

	public List<Event> getEvents()
	{

		try
		{
			logger.info("EventController.getEvents ok");
			return service.getAllEvents();

		} catch (Throwable t)
		{
			logger.error("Fehler Controller: Event konnte nicht hinzugef�gt werden", t);
		}

		return null;

	}

	public List<Event> getEventsFromNow()
	{

		try
		{
			logger.info("EventController.getEventsFromNow ok");
			return service.getEventFromNow();

		} catch (Throwable t)
		{
			logger.error("Fehler Controller: Event konnte nicht hinzugef�gt werden", t);
		}

		return null;

	}
	
	public List<Event> getEventsFromOrganizer()
	{
		try
		{
			//beim Login wurde Session Attribut "currentUser" gesetzt		
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = req.getSession(true);		
			
			Organizer temp = (Organizer) session.getAttribute("currentUser");
			Organizer o = service.getOrganizerByEmail(temp.getEmail());
			
			logger.info("EventController.getEventsFromOrganizer ok");
			return service.getEventFromOrganizer(o.getNr());

		} catch (Throwable t)
		{
			logger.error("Fehler Controller: Event konnte nicht hinzugef�gt werden", t);
		}

		return null;
	}
	
	public void removeEvent(Event event)
	{
		try
		{			
			
			service.removeEvent(event.getNr());
			logger.info("EventController.removeEvent ok");
			

		} catch (Throwable t)
		{
			logger.error("Fehler EventController.removeEvent: Event konnte nicht gelöscht werden", t);
		}

	}
	
	public void updateEvent(Event event)
	{
		try
		{			
			
			service.updateEvent(event.getNr());
			logger.info("EventController.updateEvent ok");
			

		} catch (Throwable t)
		{
			logger.error("Fehler EventController.updateEvent: Event konnte nicht geändert werden", t);
		}

	}

	public Event getE()
	{
		return e;
	}

	public void setE(Event e)
	{
		this.e = e;
	}

	public Location getL()
	{
		return l;
	}

	public void setL(Location l)
	{
		this.l = l;
	}

	public Place getP()
	{
		return p;
	}

	public void setP(Place p)
	{
		this.p = p;
	}
	
	//Getter Setter
	
	

}

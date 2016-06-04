package at.kfiw.valley3.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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

	@ManagedProperty(value = "#{event}")
	private Event e;

	@ManagedProperty(value = "#{location}")
	private Location l;

	@ManagedProperty(value = "#{place}")
	private Place p;

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	public String addEvent()
	{

		Place existingPlace = null;

		Location existingLocation = null;

		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = req.getSession(true);

		Organizer tempOrganizer = (Organizer) session
				.getAttribute("currentUser");
		
		Organizer o = null;
		
		try
		{
			o = service.getOrganizerByEmail(tempOrganizer.getEmail());	
		}
		catch (Exception e)
		{
			return "errorAddEvent";
		}
		

		if (o != null)
		{
			try
			{
				existingPlace = service.getPlaceByPlz(p.getPlz());
				existingLocation = service.getLocationByNameAndPlz(l.getName(),
						p.getPlz());

				// wenn PLZ bereits in DB in vorhanden, dann neuen Place hinzufügen
				if (existingPlace == null)
				{
					l.setPlace(p);
					service.addPlace(p);
				} else
				{
					l.setPlace(existingPlace);
				}

				if (existingLocation == null)
				{
					e.setLocation(l);
					service.addLocation(l);
				} else
				{
					e.setLocation(existingLocation);
				}

				e.setOrganizer(o);
				service.addEvent(e);
				logger.info("EventController.addEvent ok");
				clear();
				return "organizerArea";

			} catch (Exception e)
			{
				logger.info(
						"Fehler EventController.addEvent(): Event konnte nicht hinzugefügt werden",
						e);
				return "errorAddEvent";

			} 
//			finally
//			{
//				//Inhalt der Felder löschen
//				clear();
//			}
		} else
		{
			return "errorAddEvent";
		}
		
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
		p.setPlz((short) 0);
		p.setPlace(null);
		l.setName(null);
		e.setTicketsTotal((short) 0);
		e.setTicketInfo(null);
		e.setContributor(null);
	}


	public List<Event> getEventsFromNow()
	{

		try
		{
			logger.info("EventController.getEventsFromNow ok");
			return service.getEventFromNow();

		} catch (Exception t)
		{
			logger.error(
					"Fehler EventController: Events konnte nicht angezeigt werden",
					t);
			return null;			
		}		
	}

	public List<Event> getEventsFromOrganizer()
	{
		try
		{
			// beim Login wurde Session Attribut "currentUser" gesetzt
			HttpServletRequest req = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = req.getSession(true);

			Organizer temp = (Organizer) session.getAttribute("currentUser");
			Organizer o = service.getOrganizerByEmail(temp.getEmail());

			logger.info("EventController.getEventsFromOrganizer ok");
			return service.getEventFromOrganizer(o.getNr());

		} catch (Exception e)
		{
			logger.error(
					"Fehler EventController.getEventsFromOrganizer()",
					e);
			return null;
		}

		
	}

	public String removeEvent(Event event)
	{
		try
		{
			service.removeEvent(event.getNr());
			logger.info("EventController.removeEvent ok");
			return null;

		} catch (Exception e)
		{
			logger.error(
					"Fehler EventController.removeEvent: Event konnte nicht gelöscht werden",
					e);
			return "errorRemoveEvent";
		}
	}
	
	
	
	//Exceptionhandling
	public void updateEvent()
	{
		try
		{
			System.out.println(e.getNr());
			service.updateEvent(e.getNr());
			logger.info("EventController.updateEvent ok");

		} catch (Throwable t)
		{
			logger.error(
					"Fehler EventController.updateEvent: Event konnte nicht aktualisiert werden",
					t);
		}
	}

	
	//Getter/Setter
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
}

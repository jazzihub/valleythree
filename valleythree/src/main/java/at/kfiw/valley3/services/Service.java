package at.kfiw.valley3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Location;
import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.entities.Reservation;
import at.kfiw.valley3.entities.Visitor;

@ApplicationScoped
@ManagedBean(eager = true)
// im Gegensatz zu @Statefull kann ein stateless Klasse keine Informationen
// speichern
// bei jedem Aufruf müssen alle Parameter mitgegeben werden
@Stateless
public class Service
{
	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	@PersistenceContext(unitName = "valleythree")
	protected EntityManager entityManager;

	// Event:
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents()
	{
		try
		{
			Query query = entityManager.createNamedQuery(Event.NQ_FIND_ALL,
					Event.class);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getAllEvents", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventByBegin(Date begin)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_BY_BEGIN, Event.class);
			query.setParameter("begin", begin);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventByBegin", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventByBeginAndEnd(Date begin, Date end)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_BY_BEGIN_END, Event.class);
			query.setParameter("begin", begin);
			query.setParameter("end", end);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventByBeginAndEnd", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventFromOrganizer(int organizerNr)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_FROM_ORGANIZER, Event.class)
					.setParameter("organizerNr", organizerNr);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventFromNow", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventFromNow()
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_FROM_NOW, Event.class);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventFromNow", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventByKind(String kind)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_BY_KIND, Event.class);
			query.setParameter("kind", kind);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventByKind", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventByPlace(String place)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_BY_PLACE, Event.class);
			query.setParameter("place", place);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventByPlace", t);
		}

		return new ArrayList<Event>();
	}

	public void addEvent(Event e)
	{
		try
		{
			//Da SessionScoped ist beim 2. Mal hinzufügen eines Events die Nr schon vergeben, deshalb wieder auf 0 setzen,
			//damit Objekt persistiert werden kann (sonst Fehler: distached Entity passed to persist)
			if (e.getNr() != 0)
			{
				e.setNr(0);
			}
			entityManager.persist(e);
			
			logger.info("Service.addEvent ok");
		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addEvent: Event konnte nicht hinzugef�gt werden",
					t);
		}
	}

	public List<Event> searchEvent(String sql)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createQuery(sql,
					Event.class);

			logger.info("Service.searchEvent ok");

			List<Event> result = query.getResultList();
			return result;
		} catch (Throwable t)
		{
			logger.error("Fehler: Service.searchEvent", t);
		}

		return new ArrayList<Event>();
	}

	public List<Event> getEventbyDateTime(Date begin)
	{
		try
		{
			TypedQuery<Event> query = entityManager.createNamedQuery(
					Event.NQ_GET_EVENT_BY_DATE_TIME, Event.class);
			query.setParameter("begin", begin);
			// query.setParameter("time", time);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventByDateTime(Date begin)", t);
		}

		return new ArrayList<Event>();
	}
	
	public List<Event> getEventsbyDate(String begin)
	{
		try
		{
			Query query = entityManager
					.createQuery("SELECT e FROM Event e WHERE e.begin = '" + begin + "'");
			
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getEventsByDateTime(String date)", t);
		}

		return new ArrayList<Event>();
	}
	
		
	
	
	public void removeEvent(int nr)
	{
		try
		{
			Event e = entityManager.getReference(Event.class, nr);
			entityManager.remove(e);
			logger.info("Service.removeEvent ok");

		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.removeEvent: Event konnte nicht gelöscht werden",
					t);
		}
	}

	public void updateEvent(int nr, short number)
	{
		try
		{
			Event e = entityManager.find(Event.class, nr);
			e.setTicketsTotal(number);
			entityManager.merge(e);
			logger.info("Service.updateEvent ok");

		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.updateEvent: Event konnte nicht geändert werden",
					t);
		}
	}

	// Reservation:
	public void addReservation(Reservation r)
	{
		try
		{
			if (r.getNr() != 0)
			{
				r.setNr(0);
				
			} 
			entityManager.persist(r);
			logger.info("Service.addReservation ok");

		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addEvent: Reservation konnte nicht hinzugefügt werden",
					t);
		}
	}
	
	public List<Reservation> getReservationsByEvent(int nr)
	{
		try
		{
			TypedQuery<Reservation> query = entityManager.createNamedQuery(
					Reservation.NQ_GET_RESERVATIONS_FROM_EVENT, Reservation.class)
					.setParameter("nr", nr);
			System.out.println("Größe der Ergebnisliste: " + query.getResultList().size());
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: Service.getReservationsByEvent", t);
		}
		
		return new ArrayList<Reservation>();
	}
	
	

	// Visitor:
	public List<Visitor> getAllVisitors()
	{
		try
		{
			TypedQuery<Visitor> query = entityManager.createNamedQuery(
					Visitor.NQ_FIND_ALL, Visitor.class);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getAllVisitors", t);
		}

		return new ArrayList<Visitor>();
	}

	public Visitor getUserByEmail(String email)
	{
		try
		{
			TypedQuery<Visitor> query = entityManager.createNamedQuery(
					Visitor.NQ_GET_USER_BY_EMAIL, Visitor.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (Throwable t)
		{
			logger.error("Fehler: getUserByEmail", t);
		}
		return null;
	}

	

	public List<Visitor> getUsersbyLastname(String lastname)
	{
		try
		{
			TypedQuery<Visitor> query = entityManager.createNamedQuery(
					Visitor.NQ_GET_USER_BY_LASTNAME, Visitor.class);
			query.setParameter("lastname", lastname);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getUserByLastname", t);
		}
		return new ArrayList<Visitor>();
	}

	public List<Visitor> getUsersbyFirstname(String firstname)
	{
		try
		{
			TypedQuery<Visitor> query = entityManager.createNamedQuery(
					Visitor.NQ_GET_USER_BY_FIRSTNAME, Visitor.class);
			query.setParameter("firstname", firstname);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: getUserByFirstname", t);
		}
		return new ArrayList<Visitor>();
	}

	public List<Visitor> getUsersByLastAndFirstname(String lastname,
			String firstname)
	{
		try
		{
			TypedQuery<Visitor> query = entityManager.createNamedQuery(
					Visitor.NQ_GET_USER_BY_LASTNAME, Visitor.class);
			query.setParameter("firstname", firstname);
			query.setParameter("lastname", lastname);
			return query.getResultList();
		} catch (Throwable t)
		{
			logger.error("Fehler: service.getUserByFirstAndLastname", t);
		}

		return new ArrayList<Visitor>();
	}

	public short countTickets(int nr)
	{
		try
		{
			Query query = entityManager
					.createQuery("Select e.ticketsTotal from Event e where e.nr = :nr");
			query.setParameter("nr", nr);
			return (short) query.getSingleResult();
		} catch (Throwable t)
		{
			logger.error("Fehler: service.countTickets", t);
		}

		return 0;
	}

	public void addVisitor(Visitor v)
	{
		try
		{
			if (v.getNr() != 0)
			{
				entityManager.merge(v);
				logger.info("Service.addVisitor ok (merge)");
			} else
			{
				entityManager.persist(v);
				logger.info("Service.addVisitor ok (persist)");
			}
			System.out.println("Service.addVisitor ok");
		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addVisitor: Besucher konnte nicht hinzugefügt werden",
					t);
		}
	}

	// Organizer
	public void addOrganizer(Organizer o)
	{
		try
		{
			// entityManager.getTransaction().begin();
			if (!entityManager.contains(o))
			{
				entityManager.persist(o);
			}
			// entityManager.getTransaction().commit();
			System.out.println("Service.addOrganizer ok");
		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addOrganizer: Organizer konnte nicht hinzugef�gt werden",
					t);
		}
	}

	public void updateOrganizer(Organizer o)
	{
		try
		{
			entityManager.merge(o);

		} catch (Throwable t)
		{
			logger.error("Fehler Service.updateOrganizer");
		}
	}

	public Organizer getOrganizerByEmail(String email)
	{

		TypedQuery<Organizer> query = entityManager.createNamedQuery(
				Organizer.NQ_GET_ORGANIZER_BY_EMAIL, Organizer.class);
		query.setParameter("email", email);

		Organizer o;
		try
		{
			o = (Organizer) query.getSingleResult();
		} catch (NoResultException e)
		{
			return null;
		}

		return o;

	}

	public boolean getOrganizerByEmailAndPassword(String email, String password)
	{

		TypedQuery<Organizer> query = entityManager.createNamedQuery(
				Organizer.NQ_GET_ORGANIZER_BY_EMAIL_AND_PASSWORD,
				Organizer.class);
		query.setParameter("email", email);
		query.setParameter("password", password);

		Organizer o;
		try
		{
			o = (Organizer) query.getSingleResult();
		} catch (NoResultException e)
		{
			return false;
		}

		return true;

	}

	// Place
	public void addPlace(Place p)
	{
		try
		{

			if (!entityManager.contains(p))
			{
				entityManager.persist(p);
			} else
			{
				entityManager.merge(p);
			}

			logger.info("Service.addPlace ok");
		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addPlace: Place konnte nicht hinzugef�gt werden",
					t);
		}
	}

	public Place getPlaceByPlz(short plz)
	{

		Place place = entityManager.find(Place.class, plz);
		if (place == null)
			return null;

		else
			return place;
	}

	// Location
	public int addLocation(Location l)
	{
		try
		{
			if (l.getNr() != 0)
			{
				l.setNr(0);
			}
			entityManager.persist(l);

		} catch (Throwable t)
		{
			logger.error(
					"Fehler Service.addLocation: Location konnte nicht hinzugef�gt werden",
					t);
		}
		return 1;
	}

	public Location getLocationByNameAndPlz(String name, short plz)
	{

		TypedQuery<Location> query = entityManager.createNamedQuery(
				"Location.getLocationByNameAndPlz", Location.class);
		query.setParameter("name", name);
		query.setParameter("plz", plz);

		Location l = null;
		try
		{
			l = (Location) query.getSingleResult();
			return l;
		} catch (NoResultException e)
		{
			return null;
		}

	}

}

package at.kfiw.valley3.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;

@Stateless
public class Service
{
	private static final Logger logger = LoggerFactory.getLogger(Service.class);
	
	@PersistenceContext(unitName="valleythree") 
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents() {
		try {
		 
		  Query query = entityManager.createNamedQuery(Event.NQ_FIND_ALL);
		  return query.getResultList();
		} catch(Throwable t) {
			logger.error("an error has occured", t);
		}
		
		return new LinkedList<Event>();
	}
	
//	public User getUserByEmail() {
//		
//	}
//	
	

}

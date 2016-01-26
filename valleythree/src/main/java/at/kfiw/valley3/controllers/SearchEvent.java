package at.kfiw.valley3.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.services.Service;

@ManagedBean
@ViewScoped
public class SearchEvent implements Serializable
{

	private static final long serialVersionUID = -33930704224027011L;
	
	@EJB
	Service service;
	
	@ManagedProperty(value="#{event}")
	private Event event;
	
	@ManagedProperty(value="#{place}")
	private Place place;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	private List<Event> events;
	
	public String search()
	{
		String term = event.getName();
		String sql = "SELECT e FROM Event e WHERE ";
				
		if(event.getName() != null)	
		{
			term = event.getName();
			//sql = sql + "(UPPER(e.name) LIKE UPPER('%" + term + "%')) OR (UPPER(e.kind) LIKE UPPER('%" + term + "%')) ";
			sql = sql + "UPPER(e.name) LIKE UPPER('"+term+"')";
			logger.info("SearchEvent.search ok");
			
		}
		
		events = service.searchEvent(sql);
		
		return "searchResult.xhtml";
		
	}

	//Getter Setter
	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}
	
	public Place getPlace()
	{
		return place;
	}
	
	public void setPlace(Place place)
	{
		this.place = place;
	}

	public List<Event> getEvents()
	{
		return events;
	}

	public void setEvents(List<Event> events)
	{
		this.events = events;
	}
	
	
	

}

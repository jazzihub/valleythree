package at.kfiw.valley3.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class EventsToThisDate
{
	
	@EJB
	Service service;
	
	private int count;
//		if (count > 0)
//		{
//						
//			FacesContext.getCurrentInstance().addMessage(null , new FacesMessage("form:date", "Hinweis: An diesem Tag finden bereits " + count + " Veranstaltungen statt"));
//		
//		}

	
	public List<Event> eventsAtThisDate()
	{
		Event e = (Event) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("event");
		
		Date date = e.getBegin();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatDate = sdf.format(date);
		
		List<Event> events = new ArrayList<Event>();		
		events = service.getEventsbyDate(formatDate);
		count = events.size();
		return events;
	}
}

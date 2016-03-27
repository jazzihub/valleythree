package at.kfiw.valley3.services;


import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.kfiw.valley3.controllers.SearchEvent;
import at.kfiw.valley3.entities.Event;

@Path("/events")
public class RestfulService
{
	@EJB
	Service service;
	

	SearchEvent searchEvent;
	
	@GET
	@Path("/allEvents")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Event> getEvents()
	{
		return service.getEventFromNow();
	}
	
	@GET
	@POST
	@Path("/searchResult/{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public List<Event> search(@PathParam("name") String name)
			//@PathParam("date") Date date, @PathParam ("place") String place)
	{
		searchEvent = new SearchEvent();
		return searchEvent.searchForApp(name);
	}	
	
	
}

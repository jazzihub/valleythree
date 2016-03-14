package at.kfiw.valley3.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.kfiw.valley3.entities.Event;

@Path("/events")
public class RestfulService
{
	@EJB
	Service service;
	
	@GET
	@Path("/allEvents")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public List<Event> getEvents()
	{
		return service.getEventFromNow();
	}
	
	@GET
	@Path("/test")
	public String test()
	{
		return "test";
	}
	
	
}

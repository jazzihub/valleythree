package at.kfiw.valley3.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Produces({MediaType.APPLICATION_JSON})
	public List<Event> getEvents()
	{
		return service.getEventFromNow();
	}
	
	@GET
	@POST
	@Path("/searchResult/{name}/{place}/{tempdate}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Event> search(@PathParam("name") String name, @PathParam ("place") String place, @PathParam("tempdate") String tempdate)
	{	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final List<Event> events;
		System.out.println(place);
		Date date = null;
		try
		{
			date = sdf.parse(tempdate);
			
		} catch (ParseException e)
		{
			
			e.printStackTrace();
		}
		
		String term;
		String term1;
		String sql = "SELECT e FROM Event e ";

		if (!place.isEmpty())
		{
			term1 = place.trim();
			sql = sql
					+ "JOIN e.location l JOIN l.place p WHERE (LOWER(p.place) LIKE LOWER('%"
					+ term1 + "%'))";

			if (!name.isEmpty())
			{
				term = name.trim();
				sql = sql + " AND ((LOWER(e.name) LIKE LOWER('%" + term
						+ "%')) OR (LOWER(e.kind) LIKE LOWER('%" + term
						+ "%'))) ";

				
				if (date != null)
				{
					String dateNew = sdf.format(date);
					sql = sql + " AND e.begin = '" + dateNew + "' AND '" + dateNew + "' >= CURRENT_DATE";
					
				}
				else
				{
					sql = sql + " AND e.begin >= CURRENT_DATE ";
				}

			} else
			{
				if (date != null)
				{
					
					String dateNew = sdf.format(date);
					sql = sql + "AND e.begin = '" + dateNew + "' AND '" + dateNew + "' >= CURRENT_DATE";
				}
			}

		} else
		{
			if (!name.isEmpty())
			{
				
				term = name.trim();
				sql = sql + " WHERE ((LOWER(e.name) LIKE LOWER('%" + term
						+ "%')) OR (LOWER(e.kind) LIKE LOWER('%" + term
						+ "%')))";
			

				if (date != null)
				{
					
					String dateNew = sdf.format(date);
					sql = sql + " AND e.begin = '" + dateNew + "' AND '" + dateNew + "' >= CURRENT_DATE";
				}
				else
				{
					sql = sql + " AND e.begin >= CURRENT_DATE ";
				}

			} else
			{

				if (date != null)
				{
					
					String dateNew = sdf.format(date);
					sql = sql + " WHERE e.begin = '" + dateNew + "' AND '" + dateNew + "' >= CURRENT_DATE";
				}
			}
		}
		
		sql = sql + " order by e.begin";
		System.out.println(sql);
		events = service.searchEvent(sql);
		return events;
	}	
	
	
}

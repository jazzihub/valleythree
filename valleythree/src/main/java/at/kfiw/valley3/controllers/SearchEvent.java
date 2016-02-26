package at.kfiw.valley3.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class SearchEvent implements Serializable
{

	private static final long serialVersionUID = -33930704224027011L;

	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);

	private List<Event> events;
	private String name;
	private Date date;
	private String place;

	public String search()
	{
		String term;
		String sql = "SELECT e FROM Event e ";

		if (!place.isEmpty())
		{
			sql = sql
					+ "JOIN e.location l JOIN l.place p WHERE (LOWER(p.place) LIKE LOWER('%"
					+ place + "%'))";

			if (!name.isEmpty())
			{
				term = name.trim();
				sql = sql + " AND (LOWER(e.name) LIKE LOWER('%" + term
						+ "%')) OR (LOWER(e.kind) LIKE LOWER('%" + term
						+ "%'))";
				// sql = sql + "e.name LIKE '%" + term + "%'";
				logger.info("SearchEvent.search ok");

			}

		} else
		{
			if (!name.isEmpty())
			{
				term = name.trim();
				sql = sql + " WHERE (LOWER(e.name) LIKE LOWER('%" + term
						+ "%')) OR (LOWER(e.kind) LIKE LOWER('%" + term
						+ "%'))";
				// sql = sql + "e.name LIKE '%" + term + "%'";
				logger.info("SearchEvent.search ok");

			}
		}

		if (date != null)
		{
			sql = sql + "WHERE e.begin = '" + date + "'";

			// DATE_FORMAT(e.begin,'%d.%m.%Y')
		}
		
		sql = sql + " order by e.begin";
		System.out.println(sql);
		events = service.searchEvent(sql);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//		sdf.format(date);
//		events = service.searchEvent(date);

		return "searchResult.xhtml";

	}

	// Getter, Setter
	public List<Event> getEvents()
	{
		return events;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

}

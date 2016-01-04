package at.kfiw.valley3.controllers;

import java.util.List;

import javax.ejb.EJB;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.services.Service;

public class EventListController
{
	//Dependency Injection
	@EJB
	Service service;
	
	public List<Event> getAllEvents() {
		return service.getAllEvents();
	}

}

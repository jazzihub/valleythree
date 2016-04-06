package at.kfiw.valley3.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Reservation;
import at.kfiw.valley3.services.Service;

@ManagedBean
@RequestScoped
public class ReservationController
{
	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(Service.class);
	private List<Reservation> reservationlist;

	public String allReservations(Event e)
	{
		
		reservationlist = service.getReservationsByEvent(e.getNr());
		logger.info("ReservationController.allReservations ok");
		return "reservations";
	}
	
	public List<Reservation> getReservations()
	{
		return reservationlist;
	}

}

package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Reservation;
import at.kfiw.valley3.entities.Visitor;
import at.kfiw.valley3.services.Service;

@ManagedBean
@SessionScoped
public class Ticket
{
	@EJB
	Service service;
	

	private Event event;
	private short anz;
	private static final Logger logger = LoggerFactory.getLogger(Ticket.class);

	
	public String reservation()
	{
		Visitor v = (Visitor) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("visitor");
		
		Reservation r = (Reservation) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("reservation");
		
		if (r.getNumberTickets() > anz)
		{
			logger.info("Nur noch " + anz + " verfügbar!");
			FacesContext.getCurrentInstance().addMessage(null , new FacesMessage("Nur noch " + anz + " Tickets verfügbar!"));
			return null;
		}
		else
		{
			try
			{
				r.setEvent(event);
				r.setVisitor(v);
				
				service.addVisitor(v);
				service.addReservation(r);

				short number = (short) (event.getTicketsTotal() - r.getNumberTickets());
				service.updateEvent(event.getNr(), number);
				logger.info("Tickets übrig: " + number + ", Tickets abgezogen: " + r.getNumberTickets());
				return "commitTicketReservation.xhtml";
			} catch (Exception e)
			{
				return "errorReservationUser.xhtml";
			}
		}
	}
	
	
	public String Event(Event e)
	{
		this.event = e;
		this.anz = e.getTicketsTotal();
		return "userRegistry.xhtml";

	}

	public short getAnz()
	{
		return anz;
	}

	
}

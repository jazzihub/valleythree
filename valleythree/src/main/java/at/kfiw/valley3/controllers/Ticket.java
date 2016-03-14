package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import at.kfiw.valley3.entities.Event;
import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Reservation;
import at.kfiw.valley3.entities.ReservationPK;
import at.kfiw.valley3.entities.Visitor;
import at.kfiw.valley3.services.Service;

@ManagedBean
@SessionScoped
public class Ticket
{
	@EJB
	Service service;
	FacesMessage message;
	
	

//	private int number;
	private Event event;
	private short anz;
	
	
	public String reservation()
	{
		Visitor v = (Visitor) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("visitor");
		
		Reservation r = (Reservation) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("reservation");
		
		r.setEvent(event);	
		r.setVisitor(v);
		ReservationPK pk = new ReservationPK();
		pk.setRBBnr(v.getNr());
		pk.setRVgVgnr(event.getNr());
		r.setId(pk);
		v.addReservation(r);
		service.addVisitor(v);
		return "commitTicketReservation.xhtml";
	}
//	public int getNumber()
//	{
//		return number;
//	}
//
//	public void setNumber(int number)
//	{
//		if (number <= anz)
//		{
//			this.number = number;
//		} else
//		{
//			message = new FacesMessage("Nur noch " + anz + " �brig!");
//			FacesContext.getCurrentInstance()
//					.addMessage("form:ticket", message);
//		}
//	}
	
	

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

	// public int howManyTicketsLeft()
	// {
	//
	//
	// short left = service.countTickets(event.getNr());
	// if(number > left)
	// {
	//
	// return 0;
	// }
	//
	// return left;
	// }
}
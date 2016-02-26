package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import at.kfiw.valley3.services.Service;

@ManagedBean
public class Ticket
{
	@EJB
	Service service;
	FacesMessage message;
	//FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private int number;

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}
	
	public int howManyTicketsLeft()
	{
		short left = service.countTickets();
		if(number > left)
		{
			message = new FacesMessage("Nur noch "+ left + " übrig!");
			FacesContext.getCurrentInstance().addMessage("form:ticket", message);
			return 0;
		}
		
		return left;
	}
}

package at.kfiw.valley3.controllers;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.kfiw.valley3.entities.Organizer;
import at.kfiw.valley3.entities.Place;
import at.kfiw.valley3.services.Service;

@ManagedBean
@Dependent
public class OrganizerController
{
	@EJB
	Service service;

	private static final Logger logger = LoggerFactory.getLogger(OrganizerController.class);
	private EncodingPassword password;

	public String addOrganizer()
	{
		Organizer o = (Organizer) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("organizer");

		Place p = (Place) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("place");
		
		Place existingPlace = null;

		if (o != null && p != null)
		{
			try
			{
				password = new EncodingPassword();
				existingPlace = service.getPlaceByPlz(p.getPlz());
				String securePassword = password.encrypt(o.getPassword());
				o.setPassword(securePassword);

				// wenn PLZ bereits in DB in vorhanden, dann neuen Place hinzufügen
				if (existingPlace == null)
				{
					o.setPlace(p);
					service.addPlace(p);
					service.addOrganizer(o);
					logger.info("OrganizerController.addOrganizer(): Place neu angelegt; Veranstalter hinzugefügt");

				}
				// sonst vorhandene PLZ verwenden
				else
				{
					o.setPlace(existingPlace);
					service.addOrganizer(o);
					logger.info("OrganizerController.addOrganizer(): Place vorhanden; Veranstalter hinzugefügt");
				}

				return "commitRegistry";

			} catch (Exception t)
			{
				logger.error("Fehler OrganizerController: Veranstalter konnte nicht hinzugefügt werden");
				return "errorRegistry";
			}
		} else
		{
			return "errorRegistry";
		}
	}
}

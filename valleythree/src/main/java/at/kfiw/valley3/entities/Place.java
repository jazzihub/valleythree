package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the o_orte database table.
 * 
 */
@ManagedBean
@RequestScoped
@Entity
@Table(name = "o_orte")
@NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p")
@Transactional
public class Place implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "o_plz")
	private short plz;

	@Column(name = "o_ort")
	private String place;

	// bi-directional many-to-one association to Location
//	@OneToMany( mappedBy = "place")
//	private List<Location> locations;

	// bi-directional many-to-one association to Organizer
//	@OneToMany(mappedBy = "place")	
//	private List<Organizer> organizers;

	public Place()
	{
//		organizers = new ArrayList<Organizer>();       !
//		locations = new ArrayList<Location>();
	}

	public short getPlz()
	{
		return this.plz;
	}

	public void setPlz(short plz)
	{
		this.plz = plz;
	}

	public String getPlace()
	{
		return this.place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

//	public List<Location> getLocations()
//	{
//		return this.locations;
//	}
//
//	public void setLocations(List<Location> locations)
//	{
//		this.locations = locations;
//	}
//
//	public Location addLocation(Location location)
//	{
//		getLocations().add(location);
//		location.setPlace(this);
//
//		return location;
//	}
//
//	public Location removeLocation(Location location)
//	{
//		getLocations().remove(location);
//		location.setPlace(null);
//
//		return location;
//	}

//	public List<Organizer> getOrganizers()
//	{
//		return this.organizers;
//	}
//
//	public void setOrganizers(List<Organizer> organizers)
//	{
//		this.organizers = organizers;
//	}

//	public Organizer addOrganizer(Organizer organizer)
//	{
//		//if (!getOrganizers().contains(organizer))
//		//{
//			getOrganizers().add(organizer);
//			
//			//System.out.println(getOrganizers().size());
//			organizer.setPlace(this);
//		//}
//
//		return organizer;
//	}
//
//	public Organizer removeOrganizer(Organizer organizer)
//	{
//		getOrganizers().remove(organizer);
//		organizer.setPlace(null);
//
//		return organizer;
//	}

}
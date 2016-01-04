package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the o_orte database table.
 * 
 */
@ManagedBean
@SessionScoped
@Entity
@Table(name="o_orte")
@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p")
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="o_plz")
	private short plz;

	@Column(name="o_ort")
	private String place;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="place")
	private List<Location> locations;

	//bi-directional many-to-one association to Organizer
	@OneToMany(mappedBy="place")
	private List<Organizer> organizers;

	public Place() {
	}

	public short getPlz() {
		return this.plz;
	}

	public void setPlz(short plz) {
		this.plz = plz;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setPlace(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setPlace(null);

		return location;
	}

	public List<Organizer> getOrganizers() {
		return this.organizers;
	}

	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}

	public Organizer addOrganizer(Organizer organizer) {
		getOrganizers().add(organizer);
		organizer.setPlace(this);

		return organizer;
	}

	public Organizer removeOrganizer(Organizer organizer) {
		getOrganizers().remove(organizer);
		organizer.setPlace(null);

		return organizer;
	}

}
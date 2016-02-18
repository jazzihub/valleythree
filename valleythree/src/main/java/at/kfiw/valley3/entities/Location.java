package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the l_location database table.
 * 
 */
@ManagedBean
@SessionScoped
@Entity

@Table(name="l_location")
@NamedQueries({
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l"),
@NamedQuery(name="Location.getLocationByNameAndPlz", query="SELECT l FROM Location l JOIN l.place p WHERE l.name = :name AND p.plz = :plz")
})


public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="l_mnr")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;

	@Column(name="l_latitude")
	private float lat;

	@Column(name="l_longitude")
	private float lon;

	@Column(name="l_name")
	private String name;

	@Column(name="l_strasse")
	private String street;

	@Column(name="l_type")
	private String type;

	//bi-directional many-to-one association to Place
	@ManyToOne//(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="l_o_plz")
	private Place place;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="location")
	private List<Event> events;

	public Location() {
		events = new ArrayList<Event>();
	}

	public int getNr() {
		return this.nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public float getLat() {
		return this.lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return this.lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setLocation(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setLocation(null);

		return event;
	}

}
package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vr_veranstalter database table.
 * 
 */
@Entity
@Table(name="vr_veranstalter")
@NamedQuery(name="Organizer.findAll", query="SELECT o FROM Organizer o")
public class Organizer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vr_vrnr")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;

	@Column(name="vr_email")
	private String email;

	@Column(name="vr_name")
	private String name;

	@Column(name="vr_passwort")
	private String password;

	@Column(name="vr_strasse")
	private String street;

	@Column(name="vr_telefon")
	private String tel;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="organizer")
	private List<Event> events;

	//bi-directional one-to-one association to Profil
	@OneToOne(mappedBy="organizer")
	private Profil profil;

	//bi-directional many-to-one association to Place
	@ManyToOne
	@JoinColumn(name="vr_o_plz")
	private Place place;

	public Organizer() {
	}

	public int getNr() {
		return this.nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setOrganizer(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setOrganizer(null);

		return event;
	}

	public Profil getProfil() {
		return this.profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}
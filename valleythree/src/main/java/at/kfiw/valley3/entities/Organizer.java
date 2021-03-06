package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;

@ManagedBean
@SessionScoped
@Entity
@Table(name="vr_veranstalter")
@NamedQueries({
@NamedQuery(name="Organizer.findAll", query="SELECT o FROM Organizer o"),
@NamedQuery(name=Organizer.NQ_GET_ORGANIZER_BY_EMAIL, query="SELECT o FROM Organizer o WHERE o.email = :email"),
@NamedQuery(name=Organizer.NQ_EXISTING_PASSWORD, query="SELECT o FROM Organizer o WHERE o.password = :password"),
@NamedQuery(name=Organizer.NQ_GET_ORGANIZER_BY_EMAIL_AND_PASSWORD, query="SELECT o FROM Organizer o WHERE o.password = :password AND o.email = :email")
})
@Transactional
public class Organizer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String NQ_EXISTING_PASSWORD = "Organizer.existingPassword";
	public static final String NQ_GET_ORGANIZER_BY_EMAIL = "Organizer.getOrganizerByEmail";
	public static final String NQ_GET_ORGANIZER_BY_EMAIL_AND_PASSWORD = "Organizer.getOrganizerByEmailAndPassword";

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
//	@OneToMany(mappedBy="organizer", cascade=CascadeType.PERSIST)
//	private List<Event> events;

	//bi-directional one-to-one association to Profil
	@OneToOne(mappedBy="organizer")
	private Profil profil;

	//bi-directional many-to-one association to Place
	@ManyToOne//(cascade=CascadeType.PERSIST)
	@JoinColumn(name="vr_o_plz")
	private Place place;

	public Organizer() {
		//events = new ArrayList<Event>();
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
package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vp_profile database table.
 * 
 */
@Entity
@Table(name="vp_profile")
@NamedQuery(name="Profil.findAll", query="SELECT p FROM Profil p")
public class Profil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vp_vr_vrnr")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vnr;

	@Lob
	@Column(name="vp_beschreibung")
	private String description;

	@Lob
	@Column(name="vp_foto")
	private byte[] photo;

	@Column(name="vp_website")
	private String website;

	//bi-directional one-to-one association to Organzizer
	@OneToOne
	@JoinColumn(name="vp_vr_vrnr")
	private Organizer organizer;

	public Profil() {
	}

	public int getVnr() {
		return this.vnr;
	}

	public void setVnr(int vpVrVrnr) {
		this.vnr = vpVrVrnr;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Organizer getOrganizer() {
		return this.organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

}
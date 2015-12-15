package valleythree.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the b_besucher database table.
 * 
 */
@Entity
@Table(name="b_besucher")
@NamedQuery(name="Visitor.findAll", query="SELECT v FROM Visitor v")
public class Visitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="b_bnr")
	private int nr;

	@Column(name="b_email")
	private String email;

	@Column(name="b_nachname")
	private String lastname;

	@Column(name="b_passwort")
	private String password;

	@Column(name="b_telefon")
	private String tel;

	@Column(name="b_username")
	private String username;

	@Column(name="b_vorname")
	private String firstname;

	//bi-directional many-to-one association to Command
	@OneToMany(mappedBy="visitor")
	private List<Command> comments;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="visitor")
	private List<Reservation> reservations;

	public Visitor() {
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

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Command> getComments() {
		return this.comments;
	}

	public void setComments(List<Command> comments) {
		this.comments = comments;
	}

	public Command addComment(Command comment) {
		getComments().add(comment);
		comment.setVisitor(this);

		return comment;
	}

	public Command removeComment(Command comment) {
		getComments().remove(comment);
		comment.setVisitor(null);

		return comment;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setVisitor(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setVisitor(null);

		return reservation;
	}

}
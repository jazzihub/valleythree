package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the b_besucher database table.
 * 
 */
@ManagedBean
@SessionScoped
@Entity
@Table(name = "b_besucher")
@NamedQueries({
		@NamedQuery(name = Visitor.NQ_FIND_ALL, query = "SELECT v FROM Visitor v"),
		@NamedQuery(name = Visitor.NQ_GET_USER_BY_EMAIL, query = "SELECT v FROM Visitor v WHERE v.email = :email"),
		//@NamedQuery(name = Visitor.NQ_GET_USER_BY_EMAIL_PASSWORD, query = "SELECT v FROM Visitor v WHERE v.email = :email and v.password = :password"),
		@NamedQuery(name = Visitor.NQ_GET_USER_BY_LASTNAME, query = "SELECT v FROM Visitor v WHERE v.lastname = :lastname"),
		@NamedQuery(name = Visitor.NQ_GET_USER_BY_FIRSTNAME, query = "SELECT v FROM Visitor v WHERE v.firstname = :firstname"),
		@NamedQuery(name = Visitor.NQ_GET_USER_BY_FIRST_AND_LASTNAME, query = "SELECT v FROM Visitor v WHERE v.lastname = :lastname AND v.firstname = :firstname")
})
@Transactional
public class Visitor implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String NQ_FIND_ALL = "Visitor.findAll";
	public static final String NQ_GET_USER_BY_EMAIL = "Visitor.getUserByEmail";
	//public static final String NQ_GET_USER_BY_EMAIL_PASSWORD = "Visitor.getUserByEmailAndPassword";
	public static final String NQ_GET_USER_BY_LASTNAME = "Visitor.getUserByLastName";
	public static final String NQ_GET_USER_BY_FIRSTNAME = "Visitor.getUserByFirstname";
	public static final String NQ_GET_USER_BY_FIRST_AND_LASTNAME = "Visitor.getUserByFirstAndLastname";
	
	@Id
	@Column(name = "b_bnr")	
	private int nr;

	@Column(name = "b_email")
	private String email;

	@Column(name = "b_nachname")
	private String lastname;

//	@Column(name = "b_passwort")
//	private String password;

	@Column(name = "b_telefon")
	private String tel;

//	@Column(name = "b_username")
//	private String username;

	@Column(name = "b_vorname")
	private String firstname;

	// bi-directional many-to-one association to Command
//	@OneToMany(mappedBy = "visitor")
//	private List<Comment> comments;

	// bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy = "visitor", cascade = CascadeType.PERSIST)
	private List<Reservation> reservations;

	public Visitor()
	{
		reservations = new ArrayList<Reservation>();
	}

	public int getNr()
	{
		return this.nr;
	}

	public void setNr(int nr)
	{
		this.nr = nr;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getLastname()
	{
		return this.lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

//	public String getPassword()
//	{
//		return this.password;
//	}
//
//	public void setPassword(String password)
//	{
//		this.password = password;
//	}

	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

//	public String getUsername()
//	{
//		return this.username;
//	}
//
//	public void setUsername(String username)
//	{
//		this.username = username;
//	}

	public String getFirstname()
	{
		return this.firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

//	public List<Comment> getComments()
//	{
//		return this.comments;
//	}
//
//	public void setComments(List<Comment> comments)
//	{
//		this.comments = comments;
//	}
//
//	public Comment addComment(Comment comment)
//	{
//		getComments().add(comment);
//		comment.setVisitor(this);
//
//		return comment;
//	}
//
//	public Comment removeComment(Comment comment)
//	{
//		getComments().remove(comment);
//		comment.setVisitor(null);
//
//		return comment;
//	}

	public List<Reservation> getReservations()
	{
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations)
	{
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation)
	{
		getReservations().add(reservation);
		reservation.setVisitor(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation)
	{
		getReservations().remove(reservation);
		reservation.setVisitor(null);

		return reservation;
	}

}
package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;


/**
 * The persistent class for the r_reservierungen database table.
 * 
 */
@Entity
@SessionScoped
@ManagedBean
@Table(name="r_reservierungen")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
//@AssociationOverrides({
//	@AssociationOverride (name = "event",
//			joinColumns = @JoinColumn(name = "vg_vgnr")),
//	@AssociationOverride (name = "r_vg_vgnr",
//			joinColumns = @JoinColumn(name = "b_bnr")) })	)
@Transactional
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private ReservationPK id;
	
	@Id
	@Column(name = "r_nr") 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;

	@Column(name="r_anzahlkarten")
	private short numberTickets;

	//bi-directional many-to-one association to Visitor
	@ManyToOne
	@JoinColumn(name="r_b_bnr") //, insertable=false, updatable=false)
	private Visitor visitor;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="r_vg_vgnr") //, insertable=false, updatable=false)
	private Event event;

	public Reservation() {
		
	}

//	public ReservationPK getId() {
//		return this.id;
//	}
//
//	public void setId(ReservationPK id) {
//		this.id = id;
//	}

	public short getNumberTickets() {
		return this.numberTickets;
	}

	public void setNumberTickets(short numberTickets) {
		this.numberTickets = numberTickets;
	}

	public Visitor getVisitor() {
		return this.visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getNr()
	{
		return nr;
	}

	public void setNr(int nr)
	{
		this.nr = nr;
	}
	
	

}
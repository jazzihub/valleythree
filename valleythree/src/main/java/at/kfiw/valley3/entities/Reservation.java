package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@SessionScoped
@ManagedBean
@Table(name="r_reservierungen")
@NamedQueries({
	@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r"),
	@NamedQuery(name= Reservation.NQ_GET_RESERVATIONS_FROM_EVENT,query="SELECT r FROM Reservation r WHERE r.event.nr = :nr")
})
		
@Transactional
public class Reservation implements Serializable 
{
	private static final long serialVersionUID = 1L;

	public static final String NQ_GET_RESERVATIONS_FROM_EVENT = "Reservation.getReservationsFromOrganizer";
	
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
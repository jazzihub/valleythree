package valleythree.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the r_reservierungen database table.
 * 
 */
@Entity
@Table(name="r_reservierungen")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReservationPK id;

	@Column(name="r_anzahlkarten")
	private short numberTickets;

	//bi-directional many-to-one association to Visitor
	@ManyToOne
	@JoinColumn(name="r_b_bnr")
	private Visitor visitor;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="r_vg_vgnr")
	private Event event;

	public Reservation() {
	}

	public ReservationPK getId() {
		return this.id;
	}

	public void setId(ReservationPK id) {
		this.id = id;
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

}
package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vg_veranstaltungen database table.
 * 
 */
@Entity
@Table(name="vg_veranstaltungen")
@NamedQuery(name=Event.NQ_FIND_ALL, query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String NQ_FIND_ALL = "Event.findAll";

	@Id
	@Column(name="vg_vgnr")
	private int nr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vg_anfang")
	private Date begin;

	@Column(name="vg_art")
	private String kind;

	@Lob
	@Column(name="vg_detailbeschreibung")
	private String detail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="vg_ende")
	private Date end;

	@Column(name="vg_kartengesamt")
	private short ticketsTotal;

	@Lob
	@Column(name="vg_karteninfo")
	private String ticketInfo;

	@Lob
	@Column(name="vg_kurzbeschreibung")
	private String description;

	@Lob
	@Column(name="vg_mitwirkende")
	private String contributor;

	@Column(name="vg_name")
	private String name;

	@Lob
	@Column(name="vg_plakat")
	private byte[] poster;

	//bi-directional many-to-one association to Command
	@OneToMany(mappedBy="event")
	private List<Command> comments;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="event")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="vg_m_mnr")
	private Location location;

	//bi-directional many-to-one association to Organzizer
	@ManyToOne
	@JoinColumn(name="vg_vr_vrnr")
	private Organzizer organizer;

	public Event() {
	}

	public int getNr() {
		return this.nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public Date getBegin() {
		return this.begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public short getTicketsTotal() {
		return this.ticketsTotal;
	}

	public void setTicketsTotal(short ticketsTotal) {
		this.ticketsTotal = ticketsTotal;
	}

	public String getTicketInfo() {
		return this.ticketInfo;
	}

	public void setTicketInfo(String ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContributor() {
		return this.contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPoster() {
		return this.poster;
	}

	public void setPoster(byte[] poster) {
		this.poster = poster;
	}

	public List<Command> getComments() {
		return this.comments;
	}

	public void setComments(List<Command> comments) {
		this.comments = comments;
	}

	public Command addComment(Command comment) {
		getComments().add(comment);
		comment.setEvent(this);

		return comment;
	}

	public Command removeComment(Command comment) {
		getComments().remove(comment);
		comment.setEvent(null);

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
		reservation.setEvent(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setEvent(null);

		return reservation;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Organzizer getOrganizer() {
		return this.organizer;
	}

	public void setOrganizer(Organzizer organizer) {
		this.organizer = organizer;
	}

}
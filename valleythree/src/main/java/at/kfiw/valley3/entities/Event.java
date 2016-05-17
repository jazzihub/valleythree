package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean
@Entity
@Table(name = "vg_veranstaltungen")
@Transactional
@NamedQueries({
		@NamedQuery(name = Event.NQ_FIND_ALL, query = "SELECT e FROM Event e"),
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_BEGIN, query = "SELECT e FROM Event e WHERE e.begin = :begin order by e.begin"), 
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_BEGIN_END, query = "SELECT e FROM Event e WHERE e.begin = :begin AND e.begin = :end order by e.begin"),
		@NamedQuery(name = Event.NQ_GET_EVENT_FROM_NOW, query = "SELECT e FROM Event e WHERE e.begin >= CURRENT_DATE order by e.begin"),
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_KIND, query = "SELECT e FROM Event e WHERE e.kind = :kind order by e.begin"),
		@NamedQuery(name = Event.NQ_GET_EVENT_FROM_ORGANIZER, query = "SELECT e FROM Event e WHERE e.organizer.nr = :organizerNr order by e.begin"),
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_DATE_TIME, query = "SELECT e FROM Event e WHERE e.begin = :begin"),
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_NAME, query = "SELECT e FROM Event e WHERE e.name = :name"),
		@NamedQuery(name = Event.NQ_GET_EVENT_BY_ID, query = "SELECT e FROM Event e WHERE e.nr = :nr")
})

public class Event implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String NQ_FIND_ALL = "Event.findAll";
	public static final String NQ_GET_EVENT_BY_BEGIN = "Event.getEventByBegin";
	public static final String NQ_GET_EVENT_BY_BEGIN_END = "Event.getEventByBeginAndEnd";
	public static final String NQ_GET_EVENT_FROM_NOW = "Event.getEventFromNow";
	public static final String NQ_GET_EVENT_BY_KIND = "Event.getEventByKind";
	public static final String NQ_GET_EVENT_BY_PLACE = "Event.getEventByPlace";
	public static final String NQ_GET_EVENT_FROM_ORGANIZER = "Event.getEventFromOrganizer";
	public static final String NQ_GET_EVENT_BY_DATE_TIME = "Event.getEventByDateTime";
	public static final String NQ_GET_EVENT_BY_NAME = "Event.getEventByName";
	public static final String NQ_GET_EVENT_BY_ID = "Event.getEventById";
	

	@Id
	@Column(name = "vg_vgnr")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vg_anfang")
	private Date begin;

	@Column(name = "vg_art")
	private String kind;

	@Lob
	@Column(name = "vg_detailbeschreibung")
	private String detail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vg_zeit")
	private Date time;

	@Column(name = "vg_kartengesamt")
	private short ticketsTotal;

	@Lob
	@Column(name = "vg_karteninfo")
	private String ticketInfo;

	@Lob
	@Column(name = "vg_kurzbeschreibung")
	private String description;

	@Lob
	@Column(name = "vg_mitwirkende")
	private String contributor;

	@Column(name = "vg_name")
	private String name;

	@Lob
	@Column(name = "vg_plakat")
	private byte[] poster;

	// bi-directional many-to-one association to Command
	//@OneToMany(mappedBy = "event")
	//private List<Comment> comments;

	// bi-directional many-to-one association to Reservation  //geändert am 17.05.: von Eager auf Lazy
//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
//	private List<Reservation> reservations;

	// bi-directional many-to-one association to Location
	@ManyToOne//(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vg_m_mnr")
	private Location location;

	// bi-directional many-to-one association to Organizer
	@ManyToOne
	@JoinColumn(name = "vg_vr_vrnr")
	private Organizer organizer;
	
	//private Part tempPoster;

	public Event()
	{
//		reservations = new ArrayList<Reservation>();
	}

	public int getNr()
	{
		return this.nr;
	}
	
	public void setNr(int nr)
	{
		this.nr = nr;
	}

	public Date getBegin()
	{
		return this.begin;
	}

	public void setBegin(Date begin)
	{
		this.begin = begin;
	}

	public String getKind()
	{
		return this.kind;
	}

	public void setKind(String kind)
	{
		this.kind = kind;
	}

	public String getDetail()
	{
		return this.detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public Date getTime()
	{
		return this.time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public short getTicketsTotal()
	{
		return this.ticketsTotal;
	}

	public void setTicketsTotal(short ticketsTotal)
	{
		this.ticketsTotal = ticketsTotal;
	}

	public String getTicketInfo()
	{
		return this.ticketInfo;
	}

	public void setTicketInfo(String ticketInfo)
	{
		this.ticketInfo = ticketInfo;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContributor()
	{
		return this.contributor;
	}

	public void setContributor(String contributor)
	{
		this.contributor = contributor;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public byte[] getPoster()
	{
		return this.poster;
	}

	public void setPoster(byte[] poster)
	{
		this.poster = poster;
	}

//	public List<Reservation> getReservations()
//	{
//		return this.reservations;
//	}
//
//	public void setReservations(List<Reservation> reservations)
//	{
//		this.reservations = reservations;
//	}

//	public Reservation addReservation(Reservation reservation)
//	{
//		getReservations().add(reservation);
//		reservation.setEvent(this);
//
//		return reservation;
//	}
//
//	public Reservation removeReservation(Reservation reservation)
//	{
//		getReservations().remove(reservation);
//		reservation.setEvent(null);
//
//		return reservation;
//	}

	public Location getLocation()
	{
		return this.location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public Organizer getOrganizer()
	{
		return this.organizer;
	}

	public void setOrganizer(Organizer organizer)
	{
		this.organizer = organizer;
	}

}
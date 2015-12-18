package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the k_kommentare database table.
 * 
 */
@Entity
@Table(name="k_kommentare")
@NamedQuery(name="Command.findAll", query="SELECT c FROM Command c")
public class Command implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommandPK id;

	@Column(name="k_bewertung")
	private byte rating;

	@Lob
	@Column(name="k_foto")
	private byte[] photo;

	@Lob
	@Column(name="k_kommentar")
	private String command;

	//bi-directional many-to-one association to Visitor
	@ManyToOne
	@JoinColumn(name="k_b_bnr", insertable=false, updatable=false)
	private Visitor visitor;

	//bi-directional many-to-one association to Event
	@ManyToOne()
	@JoinColumn(name="k_vg_vgnr", insertable=false, updatable=false)
	private Event event;

	public Command() {
	}

	public CommandPK getId() {
		return this.id;
	}

	public void setId(CommandPK id) {
		this.id = id;
	}

	public byte getRating() {
		return this.rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
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
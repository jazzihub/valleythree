package at.kfiw.valley3.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the k_kommentare database table.
 * 
 */
@Embeddable
public class CommentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="k_vg_vgnr", insertable=false, updatable=false)
	private int kVgVgnr;

	@Column(name="k_b_bnr", insertable=false, updatable=false)
	private int kBBnr;

	public CommentPK() {
	}
	public int getKVgVgnr() {
		return this.kVgVgnr;
	}
	public void setKVgVgnr(int kVgVgnr) {
		this.kVgVgnr = kVgVgnr;
	}
	public int getKBBnr() {
		return this.kBBnr;
	}
	public void setKBBnr(int kBBnr) {
		this.kBBnr = kBBnr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CommentPK)) {
			return false;
		}
		CommentPK castOther = (CommentPK)other;
		return 
			(this.kVgVgnr == castOther.kVgVgnr)
			&& (this.kBBnr == castOther.kBBnr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.kVgVgnr;
		hash = hash * prime + this.kBBnr;
		
		return hash;
	}
}
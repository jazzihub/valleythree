package at.kfiw.valley3.entities;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * The primary key class for the r_reservierungen database table.
 * 
 */
@Embeddable
public class ReservationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="r_vg_vgnr", insertable=false, updatable=false)
	private int rVgVgnr;

	
	@Column(name="r_b_bnr", insertable=false, updatable=false)
	private int rBBnr;

	public ReservationPK() {
	}
	public int getRVgVgnr() {
		return this.rVgVgnr;
	}
	public void setRVgVgnr(int rVgVgnr) {
		this.rVgVgnr = rVgVgnr;
	}
	public int getRBBnr() {
		return this.rBBnr;
	}
	public void setRBBnr(int rBBnr) {
		this.rBBnr = rBBnr;
	}
	
//	public int hashCode()
//    {
//    return (int) (rVgVgnr + rBBnr);
//  }
//    
//    @Override
//    public boolean equals(Object obj)
//    {
//          if(obj instanceof ReservationPK)
//          {
//        	  ReservationPK otherID = (ReservationPK) obj;
//                return (otherID.rVgVgnr == this.rVgVgnr) && (otherID.rBBnr == this.rBBnr);
//          }
//          return false;
//    }


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReservationPK)) {
			return false;
		}
		ReservationPK castOther = (ReservationPK)other;
		return 
			(this.rVgVgnr == castOther.rVgVgnr)
			&& (this.rBBnr == castOther.rBBnr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rVgVgnr;
		hash = hash * prime + this.rBBnr;
		
		return hash;
	}
}
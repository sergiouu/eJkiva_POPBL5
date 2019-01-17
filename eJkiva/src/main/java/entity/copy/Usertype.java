package entity.copy;
// Generated 15-ene-2019 0:02:31 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usertype generated by hbm2java
 */
@Entity
@Table(name="usertype")
public class Usertype implements java.io.Serializable {
	@Id
	@Column(name="usertypeID")
	private int usertypeId;
	@Column(name="usertype")
	private String usertype;
	@Column(name="description")
	private String description;

	public Usertype() {
	}

	public Usertype(String usertype) {
		this.usertype = usertype;
	}

	public Usertype(String usertype, String description) {
		this.usertype = usertype;
		this.description = description;
	}

	public int getUsertypeId() {
		return this.usertypeId;
	}

	public void setUsertypeId(int usertypeId) {
		this.usertypeId = usertypeId;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.usertype;
	}

}

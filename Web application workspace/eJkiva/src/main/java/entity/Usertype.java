package entity;
// Generated 13-ene-2019 23:20:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usertype generated by hbm2java
 */
@Entity
@Table(name="usertype")
public class Usertype implements java.io.Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Byte id;
	@Column(name="utype")
	private String utype;
	@Column(name="description")
	private String description;
	/*@Column(name="users")
	private Set users = new HashSet(0);*/

	public Usertype() {
	}

	public Usertype(String utype) {
		this.utype = utype;
	}

	public Usertype(String utype, String description, Set users) {
		this.utype = utype;
		this.description = description;
		//this.users = users;
	}

	public Byte getId() {
		return this.id;
	}

	public void setId(Byte userTypeId) {
		this.id = userTypeId;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}*/

}

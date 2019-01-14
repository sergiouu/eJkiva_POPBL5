package entity;
// Generated 13-ene-2019 23:20:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Departament generated by hbm2java
 */
@Entity
@Table(name="departament")
public class Departament implements java.io.Serializable {

	private byte departamentId;
	private String depName;
	private String description;
	private Set products = new HashSet(0);

	public Departament() {
	}

	public Departament(byte departamentId, String depName) {
		this.departamentId = departamentId;
		this.depName = depName;
	}

	public Departament(byte departamentId, String depName, String description, Set products) {
		this.departamentId = departamentId;
		this.depName = depName;
		this.description = description;
		this.products = products;
	}

	public byte getDepartamentId() {
		return this.departamentId;
	}

	public void setDepartamentId(byte departamentId) {
		this.departamentId = departamentId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}
package entity;
// Generated 13-ene-2019 23:20:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Workstation generated by hbm2java
 */
@Entity
@Table(name="workstation")
public class Workstation implements java.io.Serializable {

	private Byte workstationId;
	private Segment segment;
	private String workstationNam;
	private String description;
	private Boolean state;
	private Set carriesesForDestinyWorkstationId = new HashSet(0);
	private Set carriesesForInitialWorkstationId = new HashSet(0);

	public Workstation() {
	}

	public Workstation(String workstationNam) {
		this.workstationNam = workstationNam;
	}

	public Workstation(Segment segment, String workstationNam, String description, Boolean state,
			Set carriesesForDestinyWorkstationId, Set carriesesForInitialWorkstationId) {
		this.segment = segment;
		this.workstationNam = workstationNam;
		this.description = description;
		this.state = state;
		this.carriesesForDestinyWorkstationId = carriesesForDestinyWorkstationId;
		this.carriesesForInitialWorkstationId = carriesesForInitialWorkstationId;
	}

	public Byte getWorkstationId() {
		return this.workstationId;
	}

	public void setWorkstationId(Byte workstationId) {
		this.workstationId = workstationId;
	}

	public Segment getSegment() {
		return this.segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public String getWorkstationNam() {
		return this.workstationNam;
	}

	public void setWorkstationNam(String workstationNam) {
		this.workstationNam = workstationNam;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Set getCarriesesForDestinyWorkstationId() {
		return this.carriesesForDestinyWorkstationId;
	}

	public void setCarriesesForDestinyWorkstationId(Set carriesesForDestinyWorkstationId) {
		this.carriesesForDestinyWorkstationId = carriesesForDestinyWorkstationId;
	}

	public Set getCarriesesForInitialWorkstationId() {
		return this.carriesesForInitialWorkstationId;
	}

	public void setCarriesesForInitialWorkstationId(Set carriesesForInitialWorkstationId) {
		this.carriesesForInitialWorkstationId = carriesesForInitialWorkstationId;
	}

}
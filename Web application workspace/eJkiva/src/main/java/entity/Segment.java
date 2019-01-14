package entity;
// Generated 13-ene-2019 23:20:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Segment generated by hbm2java
 */
@Entity
@Table(name="segment")
public class Segment implements java.io.Serializable {

	private Byte segmentId;
	private String segment;
	private short posX;
	private short posY;
	private String description;
	private Set authomaches = new HashSet(0);
	private Set workstations = new HashSet(0);

	public Segment() {
	}

	public Segment(String segment, short posX, short posY) {
		this.segment = segment;
		this.posX = posX;
		this.posY = posY;
	}

	public Segment(String segment, short posX, short posY, String description, Set authomaches, Set workstations) {
		this.segment = segment;
		this.posX = posX;
		this.posY = posY;
		this.description = description;
		this.authomaches = authomaches;
		this.workstations = workstations;
	}

	public Byte getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(Byte segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public short getPosX() {
		return this.posX;
	}

	public void setPosX(short posX) {
		this.posX = posX;
	}

	public short getPosY() {
		return this.posY;
	}

	public void setPosY(short posY) {
		this.posY = posY;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getAuthomaches() {
		return this.authomaches;
	}

	public void setAuthomaches(Set authomaches) {
		this.authomaches = authomaches;
	}

	public Set getWorkstations() {
		return this.workstations;
	}

	public void setWorkstations(Set workstations) {
		this.workstations = workstations;
	}

}

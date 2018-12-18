package probeHibernate;
// Generated 11-dic-2018 16:27:36 by Hibernate Tools 5.1.0.Alpha1

/**
 * Workstation generated by hbm2java
 */
public class Workstation implements java.io.Serializable {

	private byte workstationId;
	private String workstationNam;
	private String description;
	private Byte segmentId;

	public Workstation() {
	}

	public Workstation(byte workstationId, String workstationNam) {
		this.workstationId = workstationId;
		this.workstationNam = workstationNam;
	}

	public Workstation(byte workstationId, String workstationNam, String description, Byte segmentId) {
		this.workstationId = workstationId;
		this.workstationNam = workstationNam;
		this.description = description;
		this.segmentId = segmentId;
	}

	public byte getWorkstationId() {
		return this.workstationId;
	}

	public void setWorkstationId(byte workstationId) {
		this.workstationId = workstationId;
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

	public Byte getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(Byte segmentId) {
		this.segmentId = segmentId;
	}

}
package probeHibernate;
// Generated 11-dic-2018 16:27:36 by Hibernate Tools 5.1.0.Alpha1

/**
 * CarriesId generated by hbm2java
 */
public class CarriesId implements java.io.Serializable {

	private Short orderProductId;
	private Byte workstationId;
	private Byte machineId;
	private Boolean state;

	public CarriesId() {
	}

	public CarriesId(Short orderProductId, Byte workstationId, Byte machineId, Boolean state) {
		this.orderProductId = orderProductId;
		this.workstationId = workstationId;
		this.machineId = machineId;
		this.state = state;
	}

	public Short getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(Short orderProductId) {
		this.orderProductId = orderProductId;
	}

	public Byte getWorkstationId() {
		return this.workstationId;
	}

	public void setWorkstationId(Byte workstationId) {
		this.workstationId = workstationId;
	}

	public Byte getMachineId() {
		return this.machineId;
	}

	public void setMachineId(Byte machineId) {
		this.machineId = machineId;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CarriesId))
			return false;
		CarriesId castOther = (CarriesId) other;

		return ((this.getOrderProductId() == castOther.getOrderProductId())
				|| (this.getOrderProductId() != null && castOther.getOrderProductId() != null
						&& this.getOrderProductId().equals(castOther.getOrderProductId())))
				&& ((this.getWorkstationId() == castOther.getWorkstationId())
						|| (this.getWorkstationId() != null && castOther.getWorkstationId() != null
								&& this.getWorkstationId().equals(castOther.getWorkstationId())))
				&& ((this.getMachineId() == castOther.getMachineId()) || (this.getMachineId() != null
						&& castOther.getMachineId() != null && this.getMachineId().equals(castOther.getMachineId())))
				&& ((this.getState() == castOther.getState()) || (this.getState() != null
						&& castOther.getState() != null && this.getState().equals(castOther.getState())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderProductId() == null ? 0 : this.getOrderProductId().hashCode());
		result = 37 * result + (getWorkstationId() == null ? 0 : this.getWorkstationId().hashCode());
		result = 37 * result + (getMachineId() == null ? 0 : this.getMachineId().hashCode());
		result = 37 * result + (getState() == null ? 0 : this.getState().hashCode());
		return result;
	}

}
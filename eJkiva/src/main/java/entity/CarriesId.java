package entity;
// Generated 15-ene-2019 0:02:31 by Hibernate Tools 5.1.0.Alpha1

/**
 * CarriesId generated by hbm2java
 */
public class CarriesId implements java.io.Serializable {

	private int orderProductId;
	private Byte initialWorkstationId;
	private Byte destinyWorkstationId;
	private Byte machineId;
	private Boolean state;

	public CarriesId() {
	}

	public CarriesId(byte orderProductId) {
		this.orderProductId = orderProductId;
	}

	public CarriesId(byte orderProductId, Byte initialWorkstationId, Byte destinyWorkstationId, Byte machineId,
			Boolean state) {
		this.orderProductId = orderProductId;
		this.initialWorkstationId = initialWorkstationId;
		this.destinyWorkstationId = destinyWorkstationId;
		this.machineId = machineId;
		this.state = state;
	}

	public int getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	public Byte getInitialWorkstationId() {
		return this.initialWorkstationId;
	}

	public void setInitialWorkstationId(Byte initialWorkstationId) {
		this.initialWorkstationId = initialWorkstationId;
	}

	public Byte getDestinyWorkstationId() {
		return this.destinyWorkstationId;
	}

	public void setDestinyWorkstationId(Byte destinyWorkstationId) {
		this.destinyWorkstationId = destinyWorkstationId;
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

		return (this.getOrderProductId() == castOther.getOrderProductId())
				&& ((this.getInitialWorkstationId() == castOther.getInitialWorkstationId())
						|| (this.getInitialWorkstationId() != null && castOther.getInitialWorkstationId() != null
								&& this.getInitialWorkstationId().equals(castOther.getInitialWorkstationId())))
				&& ((this.getDestinyWorkstationId() == castOther.getDestinyWorkstationId())
						|| (this.getDestinyWorkstationId() != null && castOther.getDestinyWorkstationId() != null
								&& this.getDestinyWorkstationId().equals(castOther.getDestinyWorkstationId())))
				&& ((this.getMachineId() == castOther.getMachineId()) || (this.getMachineId() != null
						&& castOther.getMachineId() != null && this.getMachineId().equals(castOther.getMachineId())))
				&& ((this.getState() == castOther.getState()) || (this.getState() != null
						&& castOther.getState() != null && this.getState().equals(castOther.getState())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getOrderProductId();
		result = 37 * result + (getInitialWorkstationId() == null ? 0 : this.getInitialWorkstationId().hashCode());
		result = 37 * result + (getDestinyWorkstationId() == null ? 0 : this.getDestinyWorkstationId().hashCode());
		result = 37 * result + (getMachineId() == null ? 0 : this.getMachineId().hashCode());
		result = 37 * result + (getState() == null ? 0 : this.getState().hashCode());
		return result;
	}

}

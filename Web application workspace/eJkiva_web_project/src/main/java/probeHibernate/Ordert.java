package probeHibernate;
// Generated 11-dic-2018 16:27:36 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

/**
 * Ordert generated by hbm2java
 */
public class Ordert implements java.io.Serializable {

	private short orderId;
	private Date refD;
	private Short userId;

	public Ordert() {
	}

	public Ordert(short orderId) {
		this.orderId = orderId;
	}

	public Ordert(short orderId, Date refD, Short userId) {
		this.orderId = orderId;
		this.refD = refD;
		this.userId = userId;
	}

	public short getOrderId() {
		return this.orderId;
	}

	public void setOrderId(short orderId) {
		this.orderId = orderId;
	}

	public Date getRefD() {
		return this.refD;
	}

	public void setRefD(Date refD) {
		this.refD = refD;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

}
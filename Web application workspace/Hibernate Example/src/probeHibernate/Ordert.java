package probeHibernate;
// Generated 11-dic-2018 16:27:36 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ordert generated by hbm2java
 */
@Entity
@Table(name="orderT")
public class Ordert implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderId")
	private short orderId;
	@Column(name="refD")
	private Date refD;
	@Column(name="userId")
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
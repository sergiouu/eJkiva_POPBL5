package entity;
// Generated 15-ene-2019 0:02:31 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Orderproduct generated by hbm2java
 */
@Entity
@Table(name="orderproduct")
public class Orderproduct implements java.io.Serializable {
	@Id
	@Column(name="orderProductID")
	private int orderProductId;
	@MapsId @ManyToOne
	@JoinColumn(name="orderID")
	private Order order;
	@MapsId @OneToMany
	@JoinColumn(name="productID")
	private Product product;
	@Column(name="quantity")
	private short quantity;

	public Orderproduct() {
	}

	public Orderproduct(Order order, Product product, short quantity) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	public int getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public short getQuantity() {
		return this.quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}


}

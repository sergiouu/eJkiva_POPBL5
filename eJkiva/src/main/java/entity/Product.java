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
import javax.persistence.Table;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name="product")
public class Product implements java.io.Serializable {
	@Id
	@Column(name="productID")
	private int productId;
	@MapsId @ManyToOne
	@JoinColumn(name="departamentID")
	//@Column(name="departamentID")
	private Departament departament;
	@Column(name="product_name")
	private String productName;
	@Column(name="description")
	private String description;
	@Column(name="price")
	private float price;
	//private Set orderproducts = new HashSet(0);

	public Product() {
	}

	public Product(String productName, float price) {
		this.productName = productName;
		this.price = price;
	}

	public Product(Departament departament, String productName, String description, float price) {
		this.departament = departament;
		this.productName = productName;
		this.description = description;
		this.price = price;
		//this.orderproducts = orderproducts;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Departament getDepartament() {
		return this.departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
/*
	public Set getOrderproducts() {
		return this.orderproducts;
	}

	public void setOrderproducts(Set orderproducts) {
		this.orderproducts = orderproducts;
	}*/

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.productName;
	}
	
}

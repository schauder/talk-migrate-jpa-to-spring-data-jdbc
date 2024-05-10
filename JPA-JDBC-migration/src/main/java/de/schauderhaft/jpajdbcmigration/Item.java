package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	private Long productId;
	private Integer quantity;

	@ManyToOne(cascade = CascadeType.ALL)
	Shipment shipment;

	// index in the list of items
	Integer index;
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long product) {
		this.productId = product;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

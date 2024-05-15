package de.schauderhaft.jpajdbcmigration;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.OrderBy;

import java.util.ArrayList;
import java.util.List;

@Table
public class Shipment {
	@Id
	private Long id;

	private Long customerId;

	@MappedCollection(idColumn = "SHIPMENT_ID", keyColumn = "INDEX")
	private List<Item> items = new ArrayList<>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}

package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	List<Shipment> shipments;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

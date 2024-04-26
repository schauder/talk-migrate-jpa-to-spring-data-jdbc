package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Shipment {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

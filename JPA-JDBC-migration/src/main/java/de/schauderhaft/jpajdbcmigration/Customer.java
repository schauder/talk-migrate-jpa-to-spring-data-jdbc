package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}
}

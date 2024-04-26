package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
	@Id
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
	private List<Product> products = new ArrayList<>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

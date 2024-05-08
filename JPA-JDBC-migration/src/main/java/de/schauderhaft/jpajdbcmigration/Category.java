package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
	private List<Product> products = new ArrayList<>();

	public static Category of(String name) {
		Category category = new Category();
		category.setName(name);
		return category;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

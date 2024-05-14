package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

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
}

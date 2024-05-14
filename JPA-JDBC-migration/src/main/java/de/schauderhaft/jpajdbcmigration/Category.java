package de.schauderhaft.jpajdbcmigration;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Category {
	@Id
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

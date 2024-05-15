package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Customer {

	@Id
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

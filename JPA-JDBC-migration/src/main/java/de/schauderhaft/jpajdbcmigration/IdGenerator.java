package de.schauderhaft.jpajdbcmigration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator implements BeforeConvertCallback<Category> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Category onBeforeConvert(Category category) {

		if (category.getId() == null) {
			category.setId(obtainId());
		}
		return category;
	}

	private Long obtainId() {
		return jdbcTemplate.queryForObject("values next value for category_seq", Long.class);
	}
}

package de.schauderhaft.jpajdbcmigration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator implements BeforeConvertCallback<Object> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Object onBeforeConvert(Object root) {

		if (root instanceof Category category) {
			if (category.getId() == null) {
				category.setId(obtainId("category"));
			}
		} else if (root instanceof Customer customer) {
			if (customer.getId() == null) {
				customer.setId(obtainId("customer"));
			}
		}else if (root instanceof Product product) {
			if (product.getId() == null) {
				product.setId(obtainId("product"));
			}
		}
		return root;
	}

	private Long obtainId(String sequenceNamePrefix) {
		Long idValue = jdbcTemplate.queryForObject("values next value for " + sequenceNamePrefix + "_seq", Long.class);
		System.out.println(sequenceNamePrefix + " " + idValue);
		return idValue;
	}
}

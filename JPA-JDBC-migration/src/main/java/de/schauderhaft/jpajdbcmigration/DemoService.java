package de.schauderhaft.jpajdbcmigration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DemoService {
	@Autowired
	ProductRepository products;

	Product updateProductDescription(Long productId, String newDescription) {

		Product product = products.findById(productId)
				.orElseThrow(() -> new IllegalStateException("No product with id %s found".formatted(productId)));

		product.setDescription(newDescription);
		return product;
	}
}

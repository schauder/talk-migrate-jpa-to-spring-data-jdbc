package de.schauderhaft.jpajdbcmigration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	ShipmentRepository shipments;
	@Autowired
	ProductRepository products;
	@Autowired
	TransactionTemplate txTemplate;

	@Autowired
	DemoService demoService;

	@Test
	void contextLoads() {
		Shipment saved = shipments.save(new Shipment());
	}


	@Test
	void productsGetUpdatedWithNewDescription() {
		Product saved = products.save(new Product());

		demoService.updateProductDescription(saved.getId(), "new Description");

		assertThat(products.findById(saved.getId()).orElseThrow().getDescription()).isEqualTo("new Description");
	}

}

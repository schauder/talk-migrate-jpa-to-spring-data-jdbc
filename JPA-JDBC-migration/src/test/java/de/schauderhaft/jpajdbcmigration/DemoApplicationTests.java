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

	@Test
	void shipToNewCustomer() {

		Shipment newShipment = demoService.createNewShipment("Jens");

		Shipment loaded = shipments.findById(newShipment.getId()).orElseThrow();

		assertThat(loaded.getCustomer().getName()).isEqualTo("Jens");
	}

	@Test
	void incrementQuantity() {

		Shipment shipment = new Shipment();
		Item item = new Item();
		item.setQuantity(10);
		shipment.getItems().add(item);
		item.shipment = shipment;
		Shipment saved = shipments.save(shipment);

		demoService.incrementQuantity(item.getId(), 13);

		assertThat(shipments.findById(saved.getId()).orElseThrow().getItems().get(0).getQuantity()).isEqualTo(23);

	}

}

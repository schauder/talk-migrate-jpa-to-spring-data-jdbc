package de.schauderhaft.jpajdbcmigration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DemoService {

	@Autowired
	ProductRepository products;

	@Autowired
	ShipmentRepository shipments;

	@Autowired
	CustomerRepository customers;

	@Autowired
	ItemRepository items;

	Product updateProductDescription(Long productId, String newDescription) {

		Product product = products.findById(productId)
				.orElseThrow(() -> new IllegalStateException("No product with id %s found".formatted(productId)));

		product.setDescription(newDescription);

		// JPA does automagical saving via dirty tracking

		return product;
	}

	Shipment createNewShipment(String customerName) {

		Shipment shipment = new Shipment();
		Customer customer = customers.findByName(customerName);
		if (customer == null) {
			customer = new Customer();
			customer.setName(customerName);
		}
		shipment.setCustomer(customer);


		// JPA does automagical saving via cascading

		return shipments.save(shipment);
	}

	void incrementQuantity(Long itemId, Integer increment) {
		Item item = items.findById(itemId).orElseThrow();
		Integer oldQuantity = item.getQuantity();
		item.setQuantity(oldQuantity + increment);
	}
}

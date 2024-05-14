package de.schauderhaft.jpajdbcmigration;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
	CategoryRepository categories;

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
			customer = customers.save(customer);
		}

		shipment.setCustomerId(customer.getId());

		return shipments.save(shipment);
	}

	void incrementQuantity(Long itemId, Integer increment) {

		Shipment shipment = shipments.findByItemsId(itemId);
		Item item = shipment.getItems().stream().filter(i -> i.getId().equals(itemId)).findFirst().orElseThrow();
		Integer oldQuantity = item.getQuantity();
		item.setQuantity(oldQuantity + increment);

	}

	void assignProductCategories(Long productId, Long... categoryIds) {

		Product product = products.findById(productId).orElseThrow();
		List<ProductCategory> categoryList = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			categoryList.add(ProductCategory.of(product,categories.getReferenceById(categoryId)));
		}

		product.setCategories(categoryList);
	}

	List<Product> productsByCategoryName(String categoryName) {

		List<Product> productList = products.findByCategoriesCategoryName(categoryName);
		// Yes, by asking you for the products I tried to indicate that I wanted the products and not some f-ing empty wrappers!
		Hibernate.initialize(productList);
		return productList;
	}
}

package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	ShipmentRepository shipments;
	@Autowired
	ProductRepository products;
	@Autowired
	CategoryRepository categories;
	@Autowired
	CustomerRepository customers;

	@Autowired
	TransactionTemplate txTemplate;
	@Autowired
	EntityManager entityManager;

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

		assertThat(customers.findById(loaded.getCustomerId()).orElseThrow().getName()).isEqualTo("Jens");
	}

	@Test
	void incrementQuantity() {

		Shipment shipment = new Shipment();
		Item item = new Item();
		item.setQuantity(10);
		shipment.getItems().add(item);
		item.index = shipment.getItems().size()-1;
		Shipment saved = shipments.save(shipment);

		demoService.incrementQuantity(item.getId(), 13);

		assertThat(shipments.findById(saved.getId()).orElseThrow().getItems().get(0).getQuantity()).isEqualTo(23);

	}

	@Test
	void assignCategories() {

		Product product = new Product();
		product.setName("Minion");
		product = products.save(product);

		Category toy = categories.save(Category.of("Toy"));
		Category licensed = categories.save(Category.of("Licensed"));

		demoService.assignProductCategories(product.getId(), toy.getId(), licensed.getId());
	}


	@Test
	void productsByCategory() {

		Category tool = categories.save(Category.of("Tool"));
		Category toy = categories.save(Category.of("Toy"));
		Category licensed = categories.save(Category.of("Licensed"));

		createProduct("Minion", toy, licensed);
		createProduct("Axe", tool, toy);
		createProduct("Hammer", tool); // a hammer is no toy!
		List<Product> tools = demoService.productsByCategoryName("Tool");

		assertThat(tools).extracting("name").containsExactlyInAnyOrder("Axe", "Hammer");
	}

	private void createProduct(String name, Category... categoriesToAdd) {

		txTemplate.executeWithoutResult(tx -> {
			Product product = new Product();
			product.setName(name);

			List<ProductCategory> managedCategories = new ArrayList<>();
			for (Category category : categoriesToAdd) {
				managedCategories.add(ProductCategory.of(product, categories.getReferenceById(category.getId())));
			}

			product.setCategories(managedCategories);
			products.save(product);
		});
	}


}

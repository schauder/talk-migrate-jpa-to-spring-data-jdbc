package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
	List<Product> findByCategoriesName(String categoryName);
}

package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}

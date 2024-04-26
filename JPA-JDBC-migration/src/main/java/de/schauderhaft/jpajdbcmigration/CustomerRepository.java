package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {

	 Customer findByName(String name);
}

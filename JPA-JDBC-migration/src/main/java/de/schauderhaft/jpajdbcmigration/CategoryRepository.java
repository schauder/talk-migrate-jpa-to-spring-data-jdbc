package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

interface CategoryRepository extends ListCrudRepository<Category, Long> {
	Category findByName(String name);
}

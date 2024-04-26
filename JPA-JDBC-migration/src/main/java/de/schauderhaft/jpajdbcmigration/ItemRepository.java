package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

public interface ItemRepository extends ListCrudRepository<Item, Long> {
}

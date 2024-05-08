package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.repository.ListCrudRepository;

public interface ShipmentRepository extends ListCrudRepository<Shipment, Long> {
	 Shipment findByItemsId(Long itemId);
}

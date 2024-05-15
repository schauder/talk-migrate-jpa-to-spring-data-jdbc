package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface ShipmentRepository extends ListCrudRepository<Shipment, Long> {
	@Query("""
				select * from shipment where id = (select shipment_id from item where id = :itemId)
			""")
	Shipment findByItemsId(Long itemId);
}

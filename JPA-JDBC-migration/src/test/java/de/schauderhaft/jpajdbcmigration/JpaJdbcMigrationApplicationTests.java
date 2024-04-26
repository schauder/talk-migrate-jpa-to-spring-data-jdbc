package de.schauderhaft.jpajdbcmigration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaJdbcMigrationApplicationTests {

	@Autowired
	ShipmentRepository shipmentRepository;

	@Test
	void contextLoads() {
		Shipment saved = shipmentRepository.save(new Shipment());
	}

}

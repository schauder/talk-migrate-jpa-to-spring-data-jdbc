package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}

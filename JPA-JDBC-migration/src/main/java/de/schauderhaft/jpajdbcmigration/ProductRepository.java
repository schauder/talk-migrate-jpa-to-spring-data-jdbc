package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {

	@Query("""
				select p
				from Product p
				join p.categories pc
				join Category c
				on pc.categoryId = c.id
				where c.name = :categoryName
			""")
	List<Product> findByCategoriesCategoryName(String categoryName);
}

package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {

	@Query(value = """
			select * 
			from product 
			where id in (
				select products_id 
				from product_categories pc 
				join category c 
					on pc.categories_id = c.id 
					where c.name = :categoryName
			)
			""",
			nativeQuery = true
	)
	List<Product> findByCategoriesCategoryName(String categoryName);
}

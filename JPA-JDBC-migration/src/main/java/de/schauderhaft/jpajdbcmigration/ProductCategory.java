package de.schauderhaft.jpajdbcmigration;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory {

	static ProductCategory of(Long category) {

		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryId(category);

		return productCategory;
	}

	@Column("CATEGORIES_ID")
	Long categoryId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


}

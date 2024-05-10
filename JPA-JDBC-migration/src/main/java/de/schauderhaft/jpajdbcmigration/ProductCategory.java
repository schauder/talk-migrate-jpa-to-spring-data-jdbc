package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

	static ProductCategory of(Product product, Category category) {

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProduct(product);
		productCategory.setCategory(category);

		return productCategory;
	}


	@Id
	@ManyToOne
	@JoinColumn(name = "products_id")
	Product product;

	@Id
	@ManyToOne
	@JoinColumn(name = "categories_id")
	Category category;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


}

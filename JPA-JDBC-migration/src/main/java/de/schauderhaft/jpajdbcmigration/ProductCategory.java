package de.schauderhaft.jpajdbcmigration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

	static ProductCategory of(Product product, Long category) {

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProduct(product);
		productCategory.setCategoryId(category);

		return productCategory;
	}


	@Id
	@ManyToOne
	@JoinColumn(name = "products_id")
	Product product;

	@Id
	@Column(name = "categories_id")
	Long categoryId;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


}

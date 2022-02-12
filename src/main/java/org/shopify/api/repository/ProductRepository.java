	package org.shopify.api.repository;
	
	import org.shopify.api.model.Product;
import org.springframework.data.repository.CrudRepository;
	
	public interface ProductRepository extends CrudRepository<Product, Integer> {}
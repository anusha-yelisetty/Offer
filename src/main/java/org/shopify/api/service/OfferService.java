package org.shopify.api.service;

import org.shopify.api.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements Offer {
	
	@Autowired
	ProductService productService;

	@Override
	public Product applyOffer(Product product) {
		product.setOffer("Applied Offer"); 
		productService.saveOrUpdate(product);
		return product;
	}

	@Override
	public Product removeOffer(Product product) {
		product.setOffer(null); 
		productService.saveOrUpdate(product);
		return product;
	}


}

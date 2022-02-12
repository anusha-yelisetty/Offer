package org.shopify.api.service;

import org.shopify.api.model.Product;

public interface Offer {
	
	 Product applyOffer(Product product);
	 Product removeOffer(Product product);
	 

}

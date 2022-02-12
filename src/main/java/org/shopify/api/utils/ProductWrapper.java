package org.shopify.api.utils;

import java.util.List;

import org.shopify.api.model.Product;

public class ProductWrapper {
	private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setPersons(List<Product> products) {
        this.products = products;
    }
}

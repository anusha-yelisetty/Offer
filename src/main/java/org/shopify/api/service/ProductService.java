package org.shopify.api.service;
import java.util.ArrayList;
import java.util.List;

import org.shopify.api.model.Product;
import org.shopify.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public void saveOrUpdate(Product product) {
    	productRepository.save(product);
    }

    public void delete(int id) {
    	productRepository.deleteById(id);
    }
    

}
package org.shopify.api.controller;
import java.util.ArrayList;
import java.util.List;

import org.shopify.api.model.Product;
import org.shopify.api.service.OfferService;
import org.shopify.api.service.ProductService;
import org.shopify.api.utils.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    
    @Autowired
    OfferService offerService;

    @GetMapping("/products")
    private List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    private void deleteProduct(@PathVariable("id") int id) {
    	productService.delete(id);
    }

    @PostMapping("/products")
    private int saveProduct(@RequestBody Product product) {
    	productService.saveOrUpdate(product);
        return product.getId();
    }
    
	@PostMapping("/products/bulk")
	private List<String> saveAllProduct(@RequestBody ProductWrapper productwrapper) {
		List<String> response = new ArrayList<String>();
		for (Product product : productwrapper.getProducts()) {
			//productService.saveOrUpdate(product);
			offerService.applyOffer(product);
			response.add("Applied Offer on product id:" + product.getId());
		}
		return response;
	}
    
    
    @PostMapping("/products/applyOffer/{id}")
    private void applyOffer(@PathVariable int id,@RequestBody Product product) throws InterruptedException {
    	Product productToUpdate = productService.getProductById(id);
    	offerService.applyOffer(productToUpdate);
    	
    	   
    }
    
    @PostMapping("/products/removeOffer/{id}")
    private void removeOffer(@PathVariable int id,@RequestBody Product product) throws InterruptedException {
    	 Product productToUpdate = productService.getProductById(id);
    	 Product productRemovedOffer = offerService.removeOffer(productToUpdate);
         offerService.removeOffer(productRemovedOffer);
         
    }
    
}
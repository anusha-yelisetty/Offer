package org.shopify.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.shopify.api.model.Product;
import org.shopify.api.service.OfferService;
import org.shopify.api.service.ProductService;
import org.shopify.api.utils.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

	@Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;
    
    @MockBean
    OfferService offerService;
    
    
    @Test
	public void retrieveAllProductsWithOffers() throws Exception {
    	
    	Product product1 = new Product();
      	product1.setId(1);
      	product1.setName("Applewatch");
      	product1.setPrice(100.99);
      	
      	Product product2 = new Product();
      	product2.setId(2);
      	product2.setName("ApplePhone");
      	product2.setPrice(200.99);
      	
      	ProductWrapper productWrapper = new ProductWrapper();
      	List<Product> listOfProducts = new ArrayList<Product>();
      	listOfProducts.add(product1);
      	listOfProducts.add(product2);
      	productWrapper.setPersons(listOfProducts);
      	Mockito.when(productService.getAllProducts()).thenReturn(listOfProducts);
	
      	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/products/bulk").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(productWrapper));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		List<String> expectedResponse = Arrays.asList("Applied Offer on product id:1","Applied Offer on product id:2");
		assertNotNull(result.getResponse());
		assertEquals(asJsonString(expectedResponse),result.getResponse().getContentAsString(), "Expected Response with Applied offers");
				
		
	}
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
}

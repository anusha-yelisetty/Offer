package org.shopify.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	    @Id
	    @GeneratedValue
	    private int id;
	    private String name;
	    private Double price;
	    private String offer;
		public String getOffer() {
			return offer;
		}
		public void setOffer(String offer) {
			this.offer = offer;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
}

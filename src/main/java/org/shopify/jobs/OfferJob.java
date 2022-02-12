package org.shopify.jobs;

import java.util.Optional;

import org.shopify.api.service.OfferService;
import org.shopify.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OfferJob {

	@Autowired
	ProductService productService;

	@Autowired
	OfferService offerService;

	@Scheduled(fixedRate = 60000)
	public void cronJobSch() {
		productService.getAllProducts().forEach(e -> {
			Optional<String> isOfferExist = Optional.ofNullable(e.getOffer());
			if (isOfferExist.isPresent()) {
				offerService.removeOffer(e);
				System.out.println("Removed Offer for product :" + e.getId());
			}
		});

	}
}

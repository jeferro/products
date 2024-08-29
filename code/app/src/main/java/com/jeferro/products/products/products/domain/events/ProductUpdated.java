package com.jeferro.products.products.products.domain.events;

import java.time.Instant;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.services.time.TimeService;

public class ProductUpdated extends ProductEvent {

	private ProductUpdated(EventId id, ProductCode productCode, String occurredBy, Instant occurredOn) {
		super(id, productCode, occurredBy, occurredOn);
	}

	public static ProductUpdated create(Product product, Auth auth) {
		var productCode = product.getCode();

		var id = EventId.create();
		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

		return new ProductUpdated(id, productCode, occurredBy, occurredOn);
	}
}
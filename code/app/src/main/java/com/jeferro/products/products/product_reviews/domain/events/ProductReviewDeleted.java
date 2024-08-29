package com.jeferro.products.products.product_reviews.domain.events;

import java.time.Instant;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.services.time.TimeService;

public class ProductReviewDeleted extends ProductReviewEvent {

    private ProductReviewDeleted(EventId id, ProductReviewId productReviewId, String occurredBy, Instant occurredOn) {
        super(id, productReviewId, occurredBy, occurredOn);
    }

    public static ProductReviewDeleted create(ProductReview productReview, Auth auth) {
        var productReviewId = productReview.getId();

		var id = EventId.create();
		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

        return new ProductReviewDeleted(id, productReviewId, occurredBy, occurredOn);
    }
}
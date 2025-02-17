package com.jeferro.products.products.product_reviews.domain.events;

import java.util.Locale;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class ProductReviewUpdated extends ProductReviewEvent {

  private Locale locale;

  private String comment;

    private ProductReviewUpdated(EventId id,
		ProductReviewId productReviewId,
		Locale locale,
		String comment) {
        super(id, productReviewId);

	  setLocale(locale);
	  setComment(comment);
    }

    public static ProductReviewUpdated create(ProductReview productReview) {
	  var id = EventId.create();

	  var productReviewId = productReview.getId();
	  var locale = productReview.getLocale();
	  var comment = productReview.getComment();

        return new ProductReviewUpdated(id, productReviewId, locale, comment);
    }

  private void setLocale(Locale locale){
	ValueValidationUtils.isNotNull(locale, "locale", this);
	this.locale = locale;
  }

  private void setComment(String comment){
	ValueValidationUtils.isNotNull(comment, "comment", this);
	this.comment = comment;
  }
}

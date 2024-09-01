package com.jeferro.products.products.product_reviews.application;

import static com.jeferro.shared.ddd.application.Roles.USER;

import java.util.Set;

import com.jeferro.products.products.product_reviews.application.params.ListProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.SilentHandler;
import org.springframework.stereotype.Component;

@Component
public class ListProductReviewHandler extends SilentHandler<ListProductReviewParams, ProductReviews> {

  private final ProductReviewsRepository productReviewsRepository;

  public ListProductReviewHandler(ProductReviewsRepository productReviewsRepository) {
	super();

	this.productReviewsRepository = productReviewsRepository;
  }

  @Override
  protected Set<String> getMandatoryUserRoles() {
	return Set.of(USER);
  }

  @Override
  protected ProductReviews handle(Context context, ListProductReviewParams params) {
	var productCode = params.getProductCode();

	return productReviewsRepository.findAllByProductCode(productCode);
  }
}

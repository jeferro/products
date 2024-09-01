package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.Params;

public class ListProductReviewParams extends Params<ProductReviews> {

  private final ProductCode productCode;

  public ListProductReviewParams(ProductCode productCode) {
	super();
	this.productCode = productCode;
  }

  public ProductCode getProductCode() {
	return productCode;
  }
}

package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.users.Username;

public class ProductReview extends AggregateRoot<ProductReviewId> {

  private String comment;

  public ProductReview(ProductReviewId id, String comment) {
	super(id);

	validateComment(comment);

	this.comment = comment;
  }

  public static ProductReview createOf(Username username, ProductId productId, String comment, Auth auth) {
	var productReviewId = ProductReviewId.createOf(username, productId);

	var productReview = new ProductReview(productReviewId, comment);

	var event = ProductReviewCreated.create(productReview, auth);
	productReview.record(event);

	return productReview;
  }

  public void update(String comment, Auth auth) {
	ensureProductReviewBelongsToUserAuth(auth);

	validateComment(comment);

	this.comment = comment;

	var event = ProductReviewUpdated.create(this, auth);
	record(event);
  }

  public void delete(Auth auth) {
	ensureProductReviewBelongsToUserAuth(auth);

	var event = ProductReviewDeleted.create(this, auth);
	record(event);
  }

  public Username getUsername() {
	return id.getUsername();
  }

  public ProductId getProductId() {
	return id.getProductId();
  }

  public String getComment() {
	return comment;
  }

  private void validateComment(String comment) {
	if (comment == null) {
	  throw ValueValidationException.createOfMessage("Comment is null");
	}
  }

  private void ensureProductReviewBelongsToUserAuth(Auth auth) {
	if (!auth.belongsToUser(getUsername())) {
	  throw ForbiddenOperationInProductReviewException.belongsToOtherUser(id, auth);
	}
  }
}

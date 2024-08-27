package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.commands.Command;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;

public class GetProductCommand extends Command<Product> {

  private ProductId productId;

  public GetProductCommand(Auth auth, ProductId productId) {
	super(auth);

	setProductId(productId);
  }

  public ProductId getProductId() {
	return productId;
  }

  private void setProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }
}

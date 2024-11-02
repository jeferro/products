package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public class UpdateProductStatusParams extends Params<Product> {

  private ProductCode productCode;

  private ProductStatus status;

  public UpdateProductStatusParams(ProductCode productCode, ProductStatus status) {
	super();

	setProductCode(productCode);
	setStatus(status);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public ProductStatus getStatus() {
	return status;
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "Product code");
	this.productCode = productCode;
  }

  private void setStatus(ProductStatus status) {
	ValueValidationUtils.isNotNull(status, "Status");
	this.status = status;
  }
}

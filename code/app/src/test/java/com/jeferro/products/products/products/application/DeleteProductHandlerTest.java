package com.jeferro.products.products.products.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jeferro.products.products.products.application.params.DeleteProductParams;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductMother;
import com.jeferro.products.products.products.domain.repositories.ProductsInMemoryRepository;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteProductHandlerTest {

  public ProductsInMemoryRepository productsInMemoryRepository;

  public EventInMemoryBus eventInMemoryBus;

  public DeleteProductHandler deleteProductHandler;

  @BeforeEach
  void beforeEach() {
	eventInMemoryBus = new EventInMemoryBus();
	productsInMemoryRepository = new ProductsInMemoryRepository();

	deleteProductHandler = new DeleteProductHandler(productsInMemoryRepository, eventInMemoryBus);
  }

  @Test
  void givenOneProduct_whenDeleteProduct_thenDeletesProduct() {
	var apple = givenAnAppleInDatabase();

	var userAuth = AuthMother.user();
	var params = new DeleteProductParams(
		apple.getId()
	);

	var result = deleteProductHandler.execute(userAuth, params);

	assertEquals(apple, result);

	assertProductDoesNotExistInDatabase();

	assertProductDeletedWasPublished(apple);
  }

  @Test
  void givenNoProducts_whenDeleteProduct_thenThrowsException() {
	var apple = ProductMother.apple();

	var userAuth = AuthMother.user();
	var params = new DeleteProductParams(
		apple.getId()
	);

	assertThrows(ProductNotFoundException.class,
		() -> deleteProductHandler.execute(userAuth, params));
  }

  private void assertProductDoesNotExistInDatabase() {
	assertTrue(productsInMemoryRepository.isEmpty());
  }

  private void assertProductDeletedWasPublished(Product product) {
	assertEquals(1, eventInMemoryBus.size());

	var event = (ProductDeleted) eventInMemoryBus.getFirstOrError();

	assertEquals(product.getId(), event.getCode());
  }

  private Product givenAnAppleInDatabase() {
	var apple = ProductMother.apple();
	productsInMemoryRepository.init(apple);
	return apple;
  }
}
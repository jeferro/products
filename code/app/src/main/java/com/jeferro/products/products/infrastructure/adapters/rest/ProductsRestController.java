package com.jeferro.products.products.infrastructure.adapters.rest;

import com.jeferro.products.components.rest.generated.apis.ProductsApi;
import com.jeferro.products.components.rest.generated.dtos.ProductInputRestDTO;
import com.jeferro.products.components.rest.generated.dtos.ProductRestDTO;
import com.jeferro.products.products.application.params.*;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductCriteriaRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductIdRestMapper;
import com.jeferro.products.products.infrastructure.adapters.rest.mappers.ProductRestMapper;
import com.jeferro.shared.application.HandlerBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsRestController implements ProductsApi {

	private final ProductRestMapper productRestMapper = ProductRestMapper.INSTANCE;

	private final ProductIdRestMapper productIdRestMapper = ProductIdRestMapper.INSTANCE;

	private final ProductCriteriaRestMapper productCriteriaRestMapper = ProductCriteriaRestMapper.INSTANCE;

	private final HandlerBus handlerBus;

	public ProductsRestController(HandlerBus handlerBus) {
		this.handlerBus = handlerBus;
	}

	@Override
	public ResponseEntity<List<ProductRestDTO>> listProducts(Integer pageNumber, Integer pageSize, String name) {
		var params = new ListProductsParams(
			productCriteriaRestMapper.toDomain(pageNumber, pageSize, name)
		);

		var products = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(products);
	}

	@Override
	public ResponseEntity<ProductRestDTO> createProduct(ProductInputRestDTO productInputRestDTO) {
		var params = new CreateProductParams(
			productInputRestDTO.getName()
		);

		var product = handlerBus.execute(params);

		return productRestMapper.toCreatedResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> getProduct(String productId) {
		var params = new GetProductParams(
			productIdRestMapper.toDomain(productId)
		);

		var product = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(product);
	}

	@Override
	public ResponseEntity<ProductRestDTO> updateProduct(String productId, ProductInputRestDTO productInputRestDTO) {
		var params = new UpdateProductParams(
				productIdRestMapper.toDomain(productId),
				productInputRestDTO.getName()
		);

		var user = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(user);
	}

	@Override
	public ResponseEntity<ProductRestDTO> deleteProduct(String productId) {
		var params = new DeleteProductParams(
			productIdRestMapper.toDomain(productId)
		);

		var user = handlerBus.execute(params);

		return productRestMapper.toOkResponseDTO(user);
	}
}

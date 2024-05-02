package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(KafkaProfile.NAME)
public class ProductUpdatedKafkaMapper extends ToDTOMapper<ProductUpdated, ProductUpdatedAvroDTO> {

	public static final ProductUpdatedKafkaMapper INSTANCE = new ProductUpdatedKafkaMapper();

	private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	public ProductUpdatedAvroDTO toDTO(ProductUpdated productUpdated) {
		return new ProductUpdatedAvroDTO(
			productIdMongoMapper.toDTO(productUpdated.getProductId()),
			productUpdated.getOccurredOn(),
			productUpdated.getOccurredBy());
	}
}
package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	ProductIdMongoMapper.class
})
@Profile(KafkaProfile.NAME)
public abstract class ProductDeletedKafkaMapper extends ToDTOMapper<ProductDeleted, ProductDeletedAvroDTO> {

	public static final ProductDeletedKafkaMapper INSTANCE = Mappers.getMapper(ProductDeletedKafkaMapper.class);
}

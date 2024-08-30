package com.jeferro.products.products.products.infrastructure.adapters.mongo.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "v1/products")
public record ProductMongoDTO(
        String id,
		ProductStatusMongoDTO status,
        String name
) { }
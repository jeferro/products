package com.jeferro.products.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductCodeMongoMapper extends IdentifierMapper<ProductCode, String> {

    public static final ProductCodeMongoMapper INSTANCE = Mappers.getMapper(ProductCodeMongoMapper.class);
}

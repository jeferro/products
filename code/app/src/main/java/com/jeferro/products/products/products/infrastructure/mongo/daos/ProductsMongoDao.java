package com.jeferro.products.products.products.infrastructure.mongo.daos;

import com.jeferro.products.products.products.infrastructure.mongo.dtos.ProductMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductsMongoDao extends MongoRepository<ProductMongoDTO, String> {

}

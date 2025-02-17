package com.jeferro.products.products.products.domain.repositories;

import java.util.Optional;

import com.jeferro.products.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.products.products.products.domain.models.Products;

public interface ProductsRepository {

    ProductCode nextId();

    void save(Product product);

    Optional<Product> findById(ProductCode productCode);

    default Product findByIdOrError(ProductCode productCode) {
        return findById(productCode)
            .orElseThrow(() -> ProductNotFoundException.createOf(productCode));
    }

    void deleteById(ProductCode productCode);

    Products findAll(ProductFilter filter);
}

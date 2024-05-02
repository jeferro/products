package com.jeferro.products.products.application;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.repositories.ProductsRepository;
import com.jeferro.products.products.domain.services.ProductFetcher;
import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.events.EventBus;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.USER;

public class UpdateProductHandler extends Handler<UpdateProductCommand, Product> {

    private static final Set<String> MANDATORY_ROLES = Set.of(USER);

    private final ProductFetcher productFetcher;

    private final ProductsRepository productsRepository;

    private final EventBus eventBus;

    public UpdateProductHandler(ProductFetcher productFetcher,
                                ProductsRepository productsRepository,
                                EventBus eventBus) {
        super(MANDATORY_ROLES);

        this.productFetcher = productFetcher;
        this.productsRepository = productsRepository;
        this.eventBus = eventBus;
    }

    @Override
    public Product handle(UpdateProductCommand command) {
        var auth = command.getAuth();
        var productId = command.getProductId();
        var name = command.getName();

        var product = productFetcher.findById(productId);

        product.update(name, auth);

        productsRepository.save(product);

        eventBus.publishAll(product);

        return product;
    }
}
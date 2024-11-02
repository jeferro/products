package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class ProductDeleted extends ProductEvent {

    private LocalizedField name;

    private ProductStatus status;

    private ProductDeleted(EventId id,
        ProductCode code,
        LocalizedField name,
        ProductStatus status) {
        super(id, code);

        setName(name);
        setStatus(status);
    }

    public static ProductDeleted create(Product product) {
        var id = EventId.create();

        var code = product.getCode();
        var name = product.getName();
        var status = product.getStatus();

        return new ProductDeleted(id, code, name, status);
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        ValueValidationUtils.isNotNull(status, "Status");
        this.status = status;
    }

    public LocalizedField getName() {
        return name;
    }

    public void setName(LocalizedField name) {
        ValueValidationUtils.isNotNull(name, "Name");
        this.name = name;
    }
}

package com.jeferro.products.parametrics.domain.models;

import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;

public class ParametricValueId extends StringIdentifier {

    public ParametricValueId(String value) {
        super(value);
    }

    public static ParametricValueId createOf(String value) {
        return new ParametricValueId(value);
    }
}

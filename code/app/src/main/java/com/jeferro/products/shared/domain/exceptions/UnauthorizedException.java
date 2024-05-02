package com.jeferro.products.shared.domain.exceptions;

public class UnauthorizedException extends ApplicationException {

    private UnauthorizedException(String message){
        super(message);
    }

    public static UnauthorizedException createOf() {
        return new UnauthorizedException("Unauthorized");
    }
}
package dev.harshita.EcomProductService.EcomProductService.exception;

public class NoProductsFoundException extends RuntimeException {
    public NoProductsFoundException(String s) {
        super(s);
    }
}

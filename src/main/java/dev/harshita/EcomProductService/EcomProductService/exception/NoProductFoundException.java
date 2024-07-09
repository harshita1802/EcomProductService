package dev.harshita.EcomProductService.EcomProductService.exception;

public class NoProductFoundException extends RuntimeException {
    public NoProductFoundException(String s) {
        super(s);
    }
}

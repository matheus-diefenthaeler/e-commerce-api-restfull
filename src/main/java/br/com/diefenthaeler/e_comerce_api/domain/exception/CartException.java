package br.com.diefenthaeler.e_comerce_api.domain.exception;

public class CartException extends RuntimeException {

    public CartException(String message) {
        super(message);
    }

    public static class CartNotFoundException extends CartException {
        public CartNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidCartOperationException extends CartException {
        public InvalidCartOperationException(String message) {
            super(message);
        }
    }
}
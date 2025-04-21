package br.com.diefenthaeler.e_comerce_api.domain.exception;

public class ProductException extends RuntimeException {
    public ProductException(String message) {
        super(message);
    }

    public static class ProductNotFoundException extends ProductException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateProductSlugException extends ProductException {
        public DuplicateProductSlugException(String message) {
            super(message);
        }
    }

    public static class InvalidProductDataException extends ProductException {
        public InvalidProductDataException(String message) {
            super(message);
        }
    }
}

package br.com.diefenthaeler.e_comerce_api.domain.exception;

public class CustomerException extends RuntimeException {

    public CustomerException(String message) {
        super(message);
    }

    public static class CustomerNotFoundException extends CustomerException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateCustomerEmailException extends CustomerException {
        public DuplicateCustomerEmailException(String message) {
            super(message);
        }
    }

    public static class InvalidCustomerDataException extends CustomerException {
        public InvalidCustomerDataException(String message) {
            super(message);
        }
    }
}

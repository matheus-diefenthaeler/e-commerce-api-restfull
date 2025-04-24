package br.com.diefenthaeler.e_comerce_api.domain.exception;

public class CategoryException extends RuntimeException {

    public CategoryException(String message) {
        super(message);
    }

    public static class CategoryNotFoundException extends CategoryException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateCategorySlugException extends CategoryException {
        public DuplicateCategorySlugException(String message) {
            super(message);
        }
    }

    public static class InvalidCategoryDataException extends CategoryException {
        public InvalidCategoryDataException(String message) {
            super(message);
        }
    }
}

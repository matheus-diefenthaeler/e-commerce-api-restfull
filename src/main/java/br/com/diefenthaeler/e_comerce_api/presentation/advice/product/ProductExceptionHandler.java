package br.com.diefenthaeler.e_comerce_api.presentation.advice.product;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.ErrorResponse;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ExceptionHandler(ProductException.DuplicateProductSlugException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateProductSlug(ProductException.DuplicateProductSlugException ex, HttpServletRequest request) {
        logger.warn("Duplicated product: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ProductException.InvalidProductDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductData(ProductException.InvalidProductDataException ex, HttpServletRequest request) {
        logger.warn("Invalid Product Data: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ProductException.ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductException.ProductNotFoundException ex, HttpServletRequest request) {
        logger.warn("Entity not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

package br.com.diefenthaeler.e_comerce_api.presentation.advice.cart;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.ErrorResponse;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CartException;
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
public class CartExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CartExceptionHandler.class);

    @ExceptionHandler(CartException.CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartNotFound(CartException.CartNotFoundException ex, HttpServletRequest request) {
        logger.warn("Cart not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CartException.InvalidCartOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCartOperation(CartException.InvalidCartOperationException ex, HttpServletRequest request) {
        logger.warn("Invalid cart operation: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
package br.com.diefenthaeler.e_comerce_api.presentation.api;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.cart.AddCartItemRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;
import br.com.diefenthaeler.e_comerce_api.application.usecase.cart.AddCartItemUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final AddCartItemUseCase addCartItemUseCase;

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItemToCart(@Valid @RequestBody AddCartItemRequest request) {
        // For now, we'll implement without authentication
        Long customerId = null; // This would be retrieved from authentication context

        CartResponse response = addCartItemUseCase.execute(request, customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

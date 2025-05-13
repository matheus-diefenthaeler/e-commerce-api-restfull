package br.com.diefenthaeler.e_comerce_api.application.usecase.cart.impl;

import br.com.diefenthaeler.e_comerce_api.application.usecase.cart.RemoveCartItemUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CartException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import br.com.diefenthaeler.e_comerce_api.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RemoveCartItemUseCaseImpl implements RemoveCartItemUseCase {

    private final CartRepository cartRepository;
    private final CartService cartService;

    @Override
    @Transactional
    public void execute(Long cartItemId) {
        // Verify if the item exists before removing
        if (!cartService.cartItemExists(cartItemId)) {
            throw new CartException.CartNotFoundException("Cart item with ID " + cartItemId + " not found");
        }

        // Remove the cart item
        cartRepository.deleteCartItem(cartItemId);
    }
}

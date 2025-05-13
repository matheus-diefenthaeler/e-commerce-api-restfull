package br.com.diefenthaeler.e_comerce_api.application.usecase.cart.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CartMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.cart.GetCartByIdUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CartException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCartByIdUseCaseImpl implements GetCartByIdUseCase {

    private final CartRepository cartRepository;

    @Override
    public CartResponse execute(String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartException.CartNotFoundException(
                        "Cart with UUID '" + uuid + "' not found"));

        return CartMapper.toResponse(cart);
    }
}

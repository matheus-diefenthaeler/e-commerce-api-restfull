package br.com.diefenthaeler.e_comerce_api.application.usecase.cart;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.cart.AddCartItemRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;

public interface AddCartItemUseCase {
    CartResponse execute(AddCartItemRequest request, Long customerId);
}
package br.com.diefenthaeler.e_comerce_api.application.usecase.cart;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;

public interface GetCartByIdUseCase {

    CartResponse execute(String uuid);
}

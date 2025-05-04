package br.com.diefenthaeler.e_comerce_api.application.usecase.product;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;

public interface GetProductByIdUseCase {

    ProductResponse execute(Integer id);
}

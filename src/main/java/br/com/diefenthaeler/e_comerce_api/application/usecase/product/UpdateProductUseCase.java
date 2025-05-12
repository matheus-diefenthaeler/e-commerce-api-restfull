package br.com.diefenthaeler.e_comerce_api.application.usecase.product;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.UpdateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;

public interface UpdateProductUseCase {

    ProductResponse execute(Integer id, UpdateProductRequest request);
}

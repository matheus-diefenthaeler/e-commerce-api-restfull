package br.com.diefenthaeler.e_comerce_api.application.usecase.product;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.CreateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;

public interface CreateProductUseCase {

    ProductResponse execute(CreateProductRequest request);
}

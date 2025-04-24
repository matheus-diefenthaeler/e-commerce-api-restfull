package br.com.diefenthaeler.e_comerce_api.application.usecase.category;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.CreateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;

public interface CreateCategoryUseCase {

    CategoryResponse execute(CreateCategoryRequest request);
}

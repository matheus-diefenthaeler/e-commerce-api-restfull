package br.com.diefenthaeler.e_comerce_api.application.usecase.category;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.category.UpdateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;

public interface UpdateCategoryUseCase {

    CategoryResponse execute(String currentSlug, UpdateCategoryRequest request);
}

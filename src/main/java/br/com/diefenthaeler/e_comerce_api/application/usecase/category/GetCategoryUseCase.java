package br.com.diefenthaeler.e_comerce_api.application.usecase.category;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;

public interface GetCategoryUseCase {

    CategoryResponse execute(String slug);
}

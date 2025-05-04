package br.com.diefenthaeler.e_comerce_api.application.usecase.category;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.PagedResponse;

public interface ListCategoriesUseCase {

    PagedResponse<CategoryResponse> execute(int page, int size);
}

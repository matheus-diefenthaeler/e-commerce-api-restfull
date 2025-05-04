package br.com.diefenthaeler.e_comerce_api.application.mapper;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.category.CreateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;

public class CategoryMapper {
    public static Category toEntity(CreateCategoryRequest request) {
        return Category.builder()
                .withName(request.getName())
                .withSlug(request.getSlug())
                .build();
    }

    public static CategoryResponse toResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setSlug(category.getSlug());

        return response;
    }

}

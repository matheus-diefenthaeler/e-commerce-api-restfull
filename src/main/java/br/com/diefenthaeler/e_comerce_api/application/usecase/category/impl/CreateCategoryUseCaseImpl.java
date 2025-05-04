package br.com.diefenthaeler.e_comerce_api.application.usecase.category.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.category.CreateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CategoryMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.CreateCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CategoryException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse execute(CreateCategoryRequest request) {
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new CategoryException.DuplicateCategorySlugException("Category with slug '" + request.getSlug() + "' already exists");
        }

        Category category = CategoryMapper.toEntity(request);

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.toResponse(savedCategory);
    }
}

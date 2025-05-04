package br.com.diefenthaeler.e_comerce_api.application.usecase.category.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.category.UpdateCategoryRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CategoryMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.UpdateCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CategoryException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse execute(String currentSlug, UpdateCategoryRequest request) {
        Category category = categoryRepository.findBySlug(currentSlug)
                .orElseThrow(()-> new CategoryException.CategoryNotFoundException("Category with slug '" + currentSlug + "' not found"));

        if (!request.getSlug().equals(currentSlug) && categoryRepository.existsBySlug(request.getSlug())) {
            throw new CategoryException.DuplicateCategorySlugException("Category with slug '" + request.getSlug() + "' already exists");
        }

        Category updatedCategory = Category.builder()
                .withId(category.getId())
                .withName(request.getName())
                .withSlug(request.getSlug())
                .build();

        Category saveCategory = categoryRepository.save(updatedCategory);

        return CategoryMapper.toResponse(saveCategory);

    }
}

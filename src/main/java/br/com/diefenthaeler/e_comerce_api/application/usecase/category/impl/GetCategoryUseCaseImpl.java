package br.com.diefenthaeler.e_comerce_api.application.usecase.category.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CategoryMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.category.GetCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CategoryException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCaseImpl implements GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse execute(String slug) {

        Category byslug = categoryRepository.findBySlug(slug).orElseThrow(() -> new CategoryException
                .CategoryNotFoundException("Category with slug '" + slug + "' doesn't exists"));

        return CategoryMapper.toResponse(byslug);
    }
}

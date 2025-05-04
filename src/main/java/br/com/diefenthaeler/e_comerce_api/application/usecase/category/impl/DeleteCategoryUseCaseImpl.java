package br.com.diefenthaeler.e_comerce_api.application.usecase.category.impl;

import br.com.diefenthaeler.e_comerce_api.application.usecase.category.DeleteCategoryUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CategoryException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public void execute(String slug) {

        categoryRepository.findBySlug(slug).orElseThrow(() -> new CategoryException
                .CategoryNotFoundException("Category with slug '" + slug + "' doesn't exists"));

        categoryRepository.deleteBySlug(slug);

    }
}

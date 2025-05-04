package br.com.diefenthaeler.e_comerce_api.application.usecase.product.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.CreateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.ProductMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.CreateProductUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse execute(CreateProductRequest request) {
        if (productRepository.existsBySlug(request.getSlug())) {
            throw new ProductException.DuplicateProductSlugException("Product with slug '" + request.getSlug() + "' already exists");
        }

        Product product = ProductMapper.toEntity(request);

        // Buscar e adicionar categorias, se fornecidas
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllByIds(request.getCategoryIds());

            // Verificar se todas as categorias foram encontradas
            if (categories.size() != request.getCategoryIds().size()) {
                List<Integer> foundIds = categories.stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());

                List<Integer> missingIds = request.getCategoryIds().stream()
                        .filter(id -> !foundIds.contains(id))
                        .collect(Collectors.toList());

                throw new ProductException.InvalidProductDataException("Categories not found: " + missingIds);
            }

            for (Category category : categories) {
                product.addCategory(category);
            }
        }

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

}

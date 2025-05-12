package br.com.diefenthaeler.e_comerce_api.application.usecase.product.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.product.UpdateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.ProductMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.product.UpdateProductUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse execute(Integer id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException.ProductNotFoundException("Product with id '" + id + "' not found"));

        // Check if slug is being changed and if new slug already exists
        if (!request.getSlug().equals(product.getSlug()) && productRepository.existsBySlug(request.getSlug())) {
            throw new ProductException.DuplicateProductSlugException("Product with slug '" + request.getSlug() + "' already exists");
        }

        Product updatedProduct = Product.builder()
                .withName(request.getName())
                .withSlug(request.getSlug())
                .withDescription(request.getDescription())
                .withPrice(request.getPrice())
                .build();

        updatedProduct.setId(id);

        // Handle categories
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllByIds(request.getCategoryIds());

            // Verify all requested categories exist
            if (categories.size() != request.getCategoryIds().size()) {
                List<Integer> foundIds = categories.stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());

                List<Integer> missingIds = request.getCategoryIds().stream()
                        .filter(catId -> !foundIds.contains(catId))
                        .collect(Collectors.toList());

                throw new ProductException.InvalidProductDataException("Categories not found: " + missingIds);
            }

            for (Category category : categories) {
                updatedProduct.addCategory(category);
            }
        }

        Product savedProduct = productRepository.save(updatedProduct);

        return ProductMapper.toResponse(savedProduct);
    }
}

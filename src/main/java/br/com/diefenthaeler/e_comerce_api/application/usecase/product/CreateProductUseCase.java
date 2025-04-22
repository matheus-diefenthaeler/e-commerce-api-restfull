package br.com.diefenthaeler.e_comerce_api.application.usecase.product;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.CreateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.ProductMapper;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse execute(CreateProductRequest request) {
        // Verificar se já existe um produto com o mesmo slug
        if (productRepository.existsBySlug(request.getSlug())) {
            throw new ProductException.DuplicateProductSlugException("Product with slug '" + request.getSlug() + "' already exists");
        }

        // Criar o produto a partir da request
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

            // Adicionar as categorias ao produto
            for (Category category : categories) {
                product.addCategory(category);
            }
        }

        // Salvar o produto
        Product savedProduct = productRepository.save(product);

        // Mapear para response e retornar
        return ProductMapper.toResponse(savedProduct);
    }

}

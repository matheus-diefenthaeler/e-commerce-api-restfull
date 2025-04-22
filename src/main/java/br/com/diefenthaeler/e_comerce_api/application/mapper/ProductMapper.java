package br.com.diefenthaeler.e_comerce_api.application.mapper;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.CreateProductRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CategoryResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.ProductResponse;
import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product toEntity(CreateProductRequest request) {
        return Product.builder()
                .withName(request.getName())
                .withSlug(request.getSlug())
                .withDescription(request.getDescription())
                .withPrice(request.getPrice())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setSlug(product.getSlug());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());

        List<CategoryResponse> categoryResponses = product.getCategories().stream()
                .map(ProductMapper::toCategoryResponse)
                .collect(Collectors.toList());
        response.setCategories(categoryResponses);

        return response;
    }

    private static CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setSlug(category.getSlug());
        return response;
    }
}

package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.ProductEntity;

import java.util.stream.Collectors;

public class ProductEntityMapper {

    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setSlug(product.getSlug());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());

        if (product.getCategories() != null && !product.getCategories().isEmpty()) {
            entity.setCategories(product.getCategories().stream()
                    .map(CategoryEntityMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        Product product = Product.builder()
                .withName(entity.getName())
                .withSlug(entity.getSlug())
                .withDescription(entity.getDescription())
                .withPrice(entity.getPrice())
                .build();

        product.setId(entity.getId());

        // Mapear categorias, se houver
        if (entity.getCategories() != null && !entity.getCategories().isEmpty()) {
            entity.getCategories().forEach(categoryEntity ->
                    product.addCategory(CategoryEntityMapper.toDomain(categoryEntity))
            );
        }

        return product;
    }
}

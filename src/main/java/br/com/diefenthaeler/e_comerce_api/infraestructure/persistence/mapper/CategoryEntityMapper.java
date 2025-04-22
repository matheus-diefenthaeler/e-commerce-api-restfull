package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CategoryEntity;

public class CategoryEntityMapper {
    public static CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setSlug(category.getSlug());
        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        return Category.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSlug(entity.getSlug())
                .build();
    }
}

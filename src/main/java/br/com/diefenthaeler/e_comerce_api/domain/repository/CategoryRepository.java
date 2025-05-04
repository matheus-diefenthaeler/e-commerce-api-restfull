package br.com.diefenthaeler.e_comerce_api.domain.repository;

import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Integer id);

    Optional<Category> findBySlug(String slug);

    List<Category> findAllByIds(List<Integer> ids);

    boolean existsBySlug(String slug);

    Category save(Category category);

    void deleteBySlug(String slug);
}

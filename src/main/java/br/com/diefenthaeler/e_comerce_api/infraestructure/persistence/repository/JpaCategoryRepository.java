package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository;

import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findBySlug(String slug);

    List<CategoryEntity> findAllByIdIn(List<Integer> ids);

    boolean existsBySlug(String slug);
}

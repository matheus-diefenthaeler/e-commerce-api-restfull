package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.adapter;

import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CategoryEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper.CategoryEntityMapper;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryRepositoryAdapter(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return jpaCategoryRepository.findById(id)
                .map(CategoryEntityMapper::toDomain);
    }

    @Override
    public Optional<Category> findBySlug(String slug) {
        return jpaCategoryRepository.findBySlug(slug)
                .map(CategoryEntityMapper::toDomain);
    }

    @Override
    public List<Category> findAllByIds(List<Integer> ids) {
        List<CategoryEntity> entities = jpaCategoryRepository.findAllByIdIn(ids);
        return entities.stream()
                .map(CategoryEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

}

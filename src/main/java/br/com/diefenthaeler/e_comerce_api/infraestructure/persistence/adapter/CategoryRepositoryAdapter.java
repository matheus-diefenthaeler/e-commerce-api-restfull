package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.adapter;

import br.com.diefenthaeler.e_comerce_api.domain.entity.category.Category;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CategoryRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CategoryEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper.CategoryEntityMapper;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public boolean existsBySlug(String slug) {
        return jpaCategoryRepository.existsBySlug(slug);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryEntityMapper.toEntity(category);
        CategoryEntity savedEntity = jpaCategoryRepository.save(entity);
        return CategoryEntityMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public void deleteBySlug(String slug) {
        jpaCategoryRepository.deleteBySlug(slug);
    }

    @Override
    public Page<Category> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> entityPage = jpaCategoryRepository.findAll(pageable);

        return new PageImpl<>(
                entityPage.getContent().stream()
                        .map(CategoryEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                pageable,
                entityPage.getTotalElements()
        );
    }

}

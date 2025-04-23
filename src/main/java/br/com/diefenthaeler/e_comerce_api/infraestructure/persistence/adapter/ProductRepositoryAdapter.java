package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.adapter;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.ProductEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper.ProductEntityMapper;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(entity);
        return ProductEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return jpaProductRepository.findById(id)
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public Optional<Product> findBySlug(String slug) {
        return jpaProductRepository.findBySlug(slug)
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public List<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = jpaProductRepository.findAll(pageable);
        return productPage.getContent().stream()
                .map(ProductEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllAdmin(int page, int size) {
        return findAll(page, size);
    }

    @Override
    public void delete(Integer id) {
        jpaProductRepository.deleteById(id);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return jpaProductRepository.existsBySlug(slug);
    }

    @Override
    public long count() {
        return jpaProductRepository.count();
    }

}

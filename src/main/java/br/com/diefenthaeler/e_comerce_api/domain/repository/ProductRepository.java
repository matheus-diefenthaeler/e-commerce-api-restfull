package br.com.diefenthaeler.e_comerce_api.domain.repository;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    Optional<Product> findBySlug(String slug);

    List<Product> findAll(int page, int size);

    List<Product> findAllAdmin(int page, int size);

    void delete(Integer id);

    boolean existsBySlug(String slug);

    long count();

}

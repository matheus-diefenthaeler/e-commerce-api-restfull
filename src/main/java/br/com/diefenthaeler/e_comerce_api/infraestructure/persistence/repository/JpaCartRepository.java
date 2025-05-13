package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository;

import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUuid(String uuid);

    Optional<CartEntity> findByCustomerId(Long customerId);

    void deleteByUuid(String uuid);
}

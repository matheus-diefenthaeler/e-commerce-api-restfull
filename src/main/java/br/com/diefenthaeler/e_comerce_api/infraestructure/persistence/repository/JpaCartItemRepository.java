package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository;

import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCartItemRepository extends JpaRepository<CartItemEntity, Long> {
}

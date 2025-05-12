package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository;

import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CustomerEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUser(UserEntity user);

    Optional<CustomerEntity> findByUserId(Long userId);

    Optional<CustomerEntity> findByUserEmail(String email);

    Page<CustomerEntity> findAll(Pageable pageable);
}
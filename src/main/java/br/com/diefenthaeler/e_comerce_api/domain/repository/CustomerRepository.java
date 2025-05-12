package br.com.diefenthaeler.e_comerce_api.domain.repository;

import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByUserEmail(String email);

    boolean existsByUserEmail(String email);

    Page<Customer> findAllPaged(int page, int size);

    void delete(Long id);
}

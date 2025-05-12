package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.adapter;

import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CustomerEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper.CustomerEntityMapper;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaCustomerRepository;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.repository.JpaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaUserRepository jpaUserRepository;

    public CustomerRepositoryAdapter(JpaCustomerRepository jpaCustomerRepository, JpaUserRepository jpaUserRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        // First save or update the user
        var userEntity = jpaUserRepository.save(
                CustomerEntityMapper.toEntity(customer).getUser()
        );

        // Then save or update the customer
        CustomerEntity customerEntity = CustomerEntityMapper.toEntity(customer);
        customerEntity.setUser(userEntity);

        CustomerEntity savedEntity = jpaCustomerRepository.save(customerEntity);
        return CustomerEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaCustomerRepository.findById(id)
                .map(CustomerEntityMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByUserEmail(String email) {
        return jpaCustomerRepository.findByUserEmail(email)
                .map(CustomerEntityMapper::toDomain);
    }

    @Override
    public boolean existsByUserEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Page<Customer> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> entityPage = jpaCustomerRepository.findAll(pageable);

        return new PageImpl<>(
                entityPage.getContent().stream()
                        .map(CustomerEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                pageable,
                entityPage.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jpaCustomerRepository.findById(id).ifPresent(customer -> {
            var userId = customer.getUser().getId();
            jpaCustomerRepository.deleteById(id);
            jpaUserRepository.deleteById(userId);
        });
    }
}

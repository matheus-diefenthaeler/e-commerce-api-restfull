package br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.CreateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CustomerMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.CreateCustomerUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponse execute(CreateCustomerRequest request) {
        // Check if customer with email already exists
        if (customerRepository.existsByUserEmail(request.getEmail())) {
            throw new CustomerException.DuplicateCustomerEmailException(
                    "Customer with email '" + request.getEmail() + "' already exists"
            );
        }

        Customer customer = CustomerMapper.toEntity(request);

        // Encode the password for security
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        customer.getUser().setPassword(encodedPassword);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toResponse(savedCustomer);
    }
}
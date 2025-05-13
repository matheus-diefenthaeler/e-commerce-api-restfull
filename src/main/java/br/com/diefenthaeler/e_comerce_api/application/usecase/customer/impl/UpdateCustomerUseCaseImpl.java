package br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.UpdateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CustomerMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.UpdateCustomerUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.user.User;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse execute(Long id, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                        "Customer with id '" + id + "' not found"
                ));

        // Check if email is being changed and if it's already in use by another customer
        if (!request.getEmail().equals(customer.getUser().getEmail())
                && customerRepository.existsByUserEmail(request.getEmail())) {
            throw new CustomerException.DuplicateCustomerEmailException(
                    "Email '" + request.getEmail() + "' is already in use by another customer"
            );
        }

        User user = customer.getUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);

        return CustomerMapper.toResponse(updatedCustomer);
    }
}
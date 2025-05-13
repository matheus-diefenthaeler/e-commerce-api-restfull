package br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CustomerMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.GetCustomerByIdUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse execute(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                        "Customer with id '" + id + "' not found"));

        return CustomerMapper.toResponse(customer);
    }
}
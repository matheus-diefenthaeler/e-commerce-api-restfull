package br.com.diefenthaeler.e_comerce_api.application.usecase.customer.impl;

import br.com.diefenthaeler.e_comerce_api.application.usecase.customer.DeleteCustomerUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public void execute(Long id) {
        // Check if customer exists
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                        "Customer with id '" + id + "' not found"));

        // Delete the customer
        customerRepository.delete(id);
    }
}
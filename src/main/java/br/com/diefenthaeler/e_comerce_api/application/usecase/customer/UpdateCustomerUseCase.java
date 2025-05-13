package br.com.diefenthaeler.e_comerce_api.application.usecase.customer;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.UpdateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;

public interface UpdateCustomerUseCase {
    CustomerResponse execute(Long id, UpdateCustomerRequest request);
}

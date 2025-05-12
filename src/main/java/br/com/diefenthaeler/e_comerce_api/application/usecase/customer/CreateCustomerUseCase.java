package br.com.diefenthaeler.e_comerce_api.application.usecase.customer;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.CreateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;

public interface CreateCustomerUseCase {
    CustomerResponse execute(CreateCustomerRequest request);
}

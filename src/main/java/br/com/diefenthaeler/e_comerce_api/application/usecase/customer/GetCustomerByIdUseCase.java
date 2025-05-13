package br.com.diefenthaeler.e_comerce_api.application.usecase.customer;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;

public interface GetCustomerByIdUseCase {
    CustomerResponse execute(Long id);
}

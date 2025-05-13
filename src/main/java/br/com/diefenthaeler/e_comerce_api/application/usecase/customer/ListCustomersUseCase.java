package br.com.diefenthaeler.e_comerce_api.application.usecase.customer;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.common.PagedResponse;

public interface ListCustomersUseCase {
    PagedResponse<CustomerResponse> execute(int page, int size);
}

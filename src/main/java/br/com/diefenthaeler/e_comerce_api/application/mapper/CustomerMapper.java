package br.com.diefenthaeler.e_comerce_api.application.mapper;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.customer.CreateCustomerRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CustomerResponse;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.user.User;

import java.util.ArrayList;

public class CustomerMapper {

    public static Customer toEntity(CreateCustomerRequest request) {
        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return new Customer(
                null,
                request.getPhone(),
                request.getAddress(),
                user,
                new ArrayList<>()
        );
    }

    public static CustomerResponse toResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getUser().getName());
        response.setEmail(customer.getUser().getEmail());
        response.setPhone(customer.getPhone());
        response.setAddress(customer.getAddress());

        return response;
    }
}

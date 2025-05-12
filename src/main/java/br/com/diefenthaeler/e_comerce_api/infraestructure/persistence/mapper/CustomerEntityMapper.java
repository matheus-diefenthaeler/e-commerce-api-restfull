package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.order.Order;
import br.com.diefenthaeler.e_comerce_api.domain.entity.user.User;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomerEntityMapper {

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setPhone(customer.getPhone());
        entity.setAddress(customer.getAddress());

        if (customer.getUser() != null) {
            entity.setUser(UserEntityMapper.toEntity(customer.getUser()));
        }

        // Orders will be handled separately to avoid circular references

        return entity;
    }

    public static Customer toDomain(CustomerEntity entity) {
        User user = null;
        if (entity.getUser() != null) {
            user = UserEntityMapper.toDomain(entity.getUser());
        }

        List<Order> orders = new ArrayList<>();
        // Orders will be populated when needed to avoid circular references

        return new Customer(
                entity.getId(),
                entity.getPhone(),
                entity.getAddress(),
                user,
                orders
        );
    }
}

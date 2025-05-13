package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartEntity;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartEntityMapper {

    public static CartEntity toEntity(Cart cart) {
        CartEntity entity = new CartEntity();
        entity.setId(cart.getId());
        entity.setUuid(cart.getUuid());
        entity.setCreatedAt(cart.getCreatedAt());

        if (cart.getCustomer() != null) {
            entity.setCustomer(CustomerEntityMapper.toEntity(cart.getCustomer()));
        }

        if (cart.getItems() != null && !cart.getItems().isEmpty()) {
            List<CartItemEntity> cartItemEntities = cart.getItems().stream()
                    .map(item -> {
                        CartItemEntity itemEntity = CartItemEntityMapper.toEntity(item);
                        itemEntity.setCart(entity); // Set the parent reference
                        return itemEntity;
                    })
                    .collect(Collectors.toList());

            entity.setItems(cartItemEntities);
        }

        return entity;
    }

    public static Cart toDomain(CartEntity entity) {
        Customer customer = null;
        if (entity.getCustomer() != null) {
            customer = CustomerEntityMapper.toDomain(entity.getCustomer());
        }

        List<CartItem> items = new ArrayList<>();
        if (entity.getItems() != null) {
            items = entity.getItems().stream()
                    .map(CartItemEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }

        Cart cart = new Cart(
                entity.getId(),
                entity.getUuid(),
                items,
                customer,
                entity.getCreatedAt()
        );

        // Set the parent reference in items
        for (CartItem item : items) {
            item.setCart(cart);
        }

        return cart;
    }
}

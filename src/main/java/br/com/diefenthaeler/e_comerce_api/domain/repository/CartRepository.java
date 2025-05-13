package br.com.diefenthaeler.e_comerce_api.domain.repository;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findByUuid(String uuid);

    Optional<Cart> findByCustomerId(Long customerId);

    Cart save(Cart cart);

    void deleteByUuid(String uuid);

    void deleteCartItem(Long cartItemId);

    boolean existsCartItemById(Long cartItemId);
}

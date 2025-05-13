package br.com.diefenthaeler.e_comerce_api.domain.service;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

import java.util.Optional;

public interface CartService {

    Cart addItemToCart(Cart cart, Product product, Long quantity);

    Cart createCartForCustomer(Customer customer);

    Cart createAnonymousCart();

    Optional<CartItem> findCartItemByProduct(Cart cart, Integer productId);
}
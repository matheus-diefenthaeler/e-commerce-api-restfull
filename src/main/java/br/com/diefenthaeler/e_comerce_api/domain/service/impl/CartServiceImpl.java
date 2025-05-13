package br.com.diefenthaeler.e_comerce_api.domain.service.impl;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.service.CartService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class CartServiceImpl implements CartService {

    @Override
    public Cart addItemToCart(Cart cart, Product product, Long quantity) {
        Optional<CartItem> existingItem = findCartItemByProduct(cart, product.getId());

        if (existingItem.isPresent()) {
            updateCartItemQuantity(existingItem.get(), quantity);
        } else {
            addNewCartItem(cart, product, quantity);
        }

        return cart;
    }

    @Override
    public Cart createCartForCustomer(Customer customer) {
        String uuid = generateCartUuid();
        Date createdAt = new Date();

        return new Cart(null, uuid, new ArrayList<>(), customer, createdAt);
    }

    @Override
    public Cart createAnonymousCart() {
        String uuid = generateCartUuid();
        Date createdAt = new Date();

        return new Cart(null, uuid, new ArrayList<>(), null, createdAt);
    }

    @Override
    public Optional<CartItem> findCartItemByProduct(Cart cart, Integer productId) {
        return cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
    }

    private void updateCartItemQuantity(CartItem item, Long additionalQuantity) {
        item.setQuantity(item.getQuantity() + additionalQuantity);
    }

    private void addNewCartItem(Cart cart, Product product, Long quantity) {
        CartItem newItem = new CartItem(null, quantity, product, cart);
        cart.getItems().add(newItem);
    }

    private String generateCartUuid() {
        return UUID.randomUUID().toString();
    }
}

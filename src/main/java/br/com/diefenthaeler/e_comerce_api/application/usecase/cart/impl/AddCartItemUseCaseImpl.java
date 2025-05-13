package br.com.diefenthaeler.e_comerce_api.application.usecase.cart.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.cart.AddCartItemRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CartMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.cart.AddCartItemUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CartException;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddCartItemUseCaseImpl implements AddCartItemUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CartResponse execute(AddCartItemRequest request, Long customerId) {
        // 1. Get the product
        Product product = findProductById(request.getProductId());

        // 2. Get or create the cart
        Cart cart = findOrCreateCart(request.getCartUuid(), customerId);

        // 3. Add or update product in cart
        addOrUpdateProductInCart(cart, product, request.getQuantity());

        // 4. Save the updated cart
        Cart savedCart = cartRepository.save(cart);

        // 5. Return the cart response
        return CartMapper.toResponse(savedCart);
    }

    private Product findProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException.ProductNotFoundException(
                        "Product with id '" + productId + "' not found"));
    }

    private Cart findOrCreateCart(String cartUuid, Long customerId) {
        // Case 1: Cart UUID is provided - find by UUID
        if (cartUuid != null && !cartUuid.isEmpty()) {
            return findCartByUuid(cartUuid);
        }

        // Case 2: Customer is authenticated - find or create customer cart
        if (customerId != null) {
            return findOrCreateCustomerCart(customerId);
        }

        // Case 3: Anonymous cart - create a new one
        return createAnonymousCart();
    }

    private Cart findCartByUuid(String cartUuid) {
        return cartRepository.findByUuid(cartUuid)
                .orElseThrow(() -> new CartException.CartNotFoundException(
                        "Cart with UUID '" + cartUuid + "' not found"));
    }

    private Cart findOrCreateCustomerCart(Long customerId) {
        Customer customer = findCustomerById(customerId);

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> createCartForCustomer(customer));
    }

    private Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                        "Customer with id '" + customerId + "' not found"));
    }

    private Cart createCartForCustomer(Customer customer) {
        String uuid = generateCartUuid();
        Date createdAt = new Date();

        Cart cart = new Cart(null, uuid, new ArrayList<>(), customer, createdAt);
        return cartRepository.save(cart);
    }

    private Cart createAnonymousCart() {
        String uuid = generateCartUuid();
        Date createdAt = new Date();

        Cart cart = new Cart(null, uuid, new ArrayList<>(), null, createdAt);
        return cartRepository.save(cart);
    }

    private void addOrUpdateProductInCart(Cart cart, Product product, Long quantity) {
        Optional<CartItem> existingItem = findCartItemByProduct(cart, product.getId());

        if (existingItem.isPresent()) {
            updateCartItemQuantity(existingItem.get(), quantity);
        } else {
            addNewCartItem(cart, product, quantity);
        }
    }

    private Optional<CartItem> findCartItemByProduct(Cart cart, Integer productId) {
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
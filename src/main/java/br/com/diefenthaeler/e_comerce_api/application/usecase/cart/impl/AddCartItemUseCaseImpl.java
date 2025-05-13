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
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductException.ProductNotFoundException(
                        "Product with id '" + request.getProductId() + "' not found"));

        // 2. Get or create the cart
        Cart cart;

        if (request.getCartUuid() != null && !request.getCartUuid().isEmpty()) {
            // If cartUuid is provided, try to find that cart
            cart = cartRepository.findByUuid(request.getCartUuid())
                    .orElseThrow(() -> new CartException.CartNotFoundException(
                            "Cart with UUID '" + request.getCartUuid() + "' not found"));
        } else if (customerId != null) {
            // If authenticated user, get or create a cart for the customer
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                            "Customer with id '" + customerId + "' not found"));

            // Try to find an existing cart for the customer
            Optional<Cart> existingCart = cartRepository.findByCustomerId(customerId);

            if (existingCart.isPresent()) {
                cart = existingCart.get();
            } else {
                // Create a new cart for the customer
                cart = createNewCart(customer);
            }
        } else {
            // Anonymous cart - create a new one with no customer
            cart = createNewCart(null);
        }

        // 3. Check if the product already exists in the cart
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // Update the quantity of the existing item
            CartItem item = existingCartItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            // Add a new item to the cart
            CartItem newItem = new CartItem(
                    null,
                    request.getQuantity(),
                    product,
                    cart
            );
            cart.getItems().add(newItem);
        }

        // 4. Save the updated cart
        Cart savedCart = cartRepository.save(cart);

        // 5. Return the cart response
        return CartMapper.toResponse(savedCart);
    }

    private Cart createNewCart(Customer customer) {
        String uuid = UUID.randomUUID().toString();
        Cart cart = new Cart(
                null,
                uuid,
                new ArrayList<>(),
                customer,
                new Date()
        );
        return cartRepository.save(cart);
    }
}

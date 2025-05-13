package br.com.diefenthaeler.e_comerce_api.application.usecase.cart.impl;

import br.com.diefenthaeler.e_comerce_api.application.dto.request.cart.AddCartItemRequest;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;
import br.com.diefenthaeler.e_comerce_api.application.mapper.CartMapper;
import br.com.diefenthaeler.e_comerce_api.application.usecase.cart.AddCartItemUseCase;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CartException;
import br.com.diefenthaeler.e_comerce_api.domain.exception.CustomerException;
import br.com.diefenthaeler.e_comerce_api.domain.exception.ProductException;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CartRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.CustomerRepository;
import br.com.diefenthaeler.e_comerce_api.domain.repository.ProductRepository;
import br.com.diefenthaeler.e_comerce_api.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddCartItemUseCaseImpl implements AddCartItemUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartService cartService;

    @Override
    @Transactional
    public CartResponse execute(AddCartItemRequest request, Long customerId) {
        // 1. Get the product
        Product product = findProductById(request.getProductId());

        // 2. Get or create the cart
        Cart cart = findOrCreateCart(request.getCartUuid(), customerId);

        // 3. Add or update product in cart - domain service
        cart = cartService.addItemToCart(cart, product, request.getQuantity());

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

        // Case 3: Anonymous cart - create a new one using domain service
        Cart anonymousCart = cartService.createAnonymousCart();
        return cartRepository.save(anonymousCart);
    }

    private Cart findCartByUuid(String cartUuid) {
        return cartRepository.findByUuid(cartUuid)
                .orElseThrow(() -> new CartException.CartNotFoundException(
                        "Cart with UUID '" + cartUuid + "' not found"));
    }

    private Cart findOrCreateCustomerCart(Long customerId) {
        Customer customer = findCustomerById(customerId);

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Cart newCart = cartService.createCartForCustomer(customer);
                    return cartRepository.save(newCart);
                });
    }

    private Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerException.CustomerNotFoundException(
                        "Customer with id '" + customerId + "' not found"));
    }
}
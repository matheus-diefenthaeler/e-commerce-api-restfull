package br.com.diefenthaeler.e_comerce_api.application.mapper;

import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartItemResponse;
import br.com.diefenthaeler.e_comerce_api.application.dto.response.CartResponse;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.Cart;
import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartResponse toResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setUuid(cart.getUuid());
        response.setCreatedAt(cart.getCreatedAt());

        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .map(CartMapper::toCartItemResponse)
                .collect(Collectors.toList());

        response.setItems(itemResponses);

        // Calculate total price
        BigDecimal totalPrice = itemResponses.stream()
                .map(CartItemResponse::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setTotalPrice(totalPrice);

        return response;
    }

    private static CartItemResponse toCartItemResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setId(item.getId());
        response.setQuantity(item.getQuantity());
        response.setProduct(ProductMapper.toResponse(item.getProduct()));

        // Calculate total price for this item
        BigDecimal totalPrice = item.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        response.setTotalPrice(totalPrice);

        return response;
    }
}

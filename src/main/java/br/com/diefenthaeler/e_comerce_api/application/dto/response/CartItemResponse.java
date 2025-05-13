package br.com.diefenthaeler.e_comerce_api.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemResponse {
    private Long id;
    private Long quantity;
    private ProductResponse product;
    private BigDecimal totalPrice;
}
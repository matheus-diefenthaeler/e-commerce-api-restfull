package br.com.diefenthaeler.e_comerce_api.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartResponse {
    private Long id;
    private String uuid;
    private Date createdAt;
    private List<CartItemResponse> items;
    private BigDecimal totalPrice;
}
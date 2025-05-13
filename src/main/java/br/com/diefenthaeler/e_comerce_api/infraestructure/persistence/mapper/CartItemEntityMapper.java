package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.mapper;

import br.com.diefenthaeler.e_comerce_api.domain.entity.cart.CartItem;
import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;
import br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity.CartItemEntity;

public class CartItemEntityMapper {

    public static CartItemEntity toEntity(CartItem item) {
        CartItemEntity entity = new CartItemEntity();
        entity.setId(item.getId());
        entity.setQuantity(item.getQuantity());

        if (item.getProduct() != null) {
            entity.setProduct(ProductEntityMapper.toEntity(item.getProduct()));
        }

        // Cart is set by the parent mapper to avoid circular references

        return entity;
    }

    public static CartItem toDomain(CartItemEntity entity) {
        Product product = null;
        if (entity.getProduct() != null) {
            product = ProductEntityMapper.toDomain(entity.getProduct());
        }

        // Cart is set by the parent mapper to avoid circular references
        return new CartItem(
                entity.getId(),
                entity.getQuantity(),
                product,
                null // Cart will be set by the parent mapper
        );
    }
}
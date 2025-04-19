package br.com.diefenthaeler.e_comerce_api.domain.entity.cart;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

public class CartItem {

    private Long id;
    private Long quantity;
    private Product product;
    private Cart cart;

    public CartItem(Long id, Long quantity, Product product, Cart cart) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

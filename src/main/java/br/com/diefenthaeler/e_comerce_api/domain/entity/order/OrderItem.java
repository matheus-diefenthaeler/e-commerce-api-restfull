package br.com.diefenthaeler.e_comerce_api.domain.entity.order;

import br.com.diefenthaeler.e_comerce_api.domain.entity.product.Product;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private Order order;
    private Product product;

    public OrderItem(Long id, Integer quantity, BigDecimal price, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

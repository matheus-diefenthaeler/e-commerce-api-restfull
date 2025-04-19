package br.com.diefenthaeler.e_comerce_api.domain.entity.cart;

import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;

import java.util.Date;
import java.util.List;

public class Cart {

    private Long id;
    private String uuid;
    private List<CartItem> items;
    private Customer customer;
    private Date createdAt;

    public Cart(Long id, String uuid, List<CartItem> items, Customer customer, Date createdAt) {
        this.id = id;
        this.uuid = uuid;
        this.items = items;
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

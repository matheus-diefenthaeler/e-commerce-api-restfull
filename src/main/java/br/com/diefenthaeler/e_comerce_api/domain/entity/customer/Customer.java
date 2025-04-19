package br.com.diefenthaeler.e_comerce_api.domain.entity.customer;

import br.com.diefenthaeler.e_comerce_api.domain.entity.order.Order;
import br.com.diefenthaeler.e_comerce_api.domain.entity.user.User;

import java.util.List;

public class Customer {

    private Long id;
    private String phone;
    private String address;
    private User user;
    private List<Order> orders;

    public Customer(Long id, String phone, String address, User user, List<Order> orders) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

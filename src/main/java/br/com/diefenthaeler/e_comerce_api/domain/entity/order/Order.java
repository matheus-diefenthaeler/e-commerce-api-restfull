package br.com.diefenthaeler.e_comerce_api.domain.entity.order;

import br.com.diefenthaeler.e_comerce_api.domain.entity.customer.Customer;
import br.com.diefenthaeler.e_comerce_api.domain.entity.payment.Payment;

import java.util.Date;
import java.util.List;

public class Order {

    private Long id;
    private Customer customer;
    private List<OrderItem> orderItems;
    private Date createdAt;
    private OrderStatus status;
    private List<Payment> payments;

    public Order(Long id, Customer customer, List<OrderItem> orderItems, Date createdAt, OrderStatus status, List<Payment> payments) {
        this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.status = status;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}

package br.com.diefenthaeler.e_comerce_api.domain.entity.payment;

import br.com.diefenthaeler.e_comerce_api.domain.entity.order.Order;

import java.util.Date;

public class Payment {

    private Long id;
    private Integer amount;
    private Date paymentDate;
    private Order order;
    private PaymentMethod method;
    private PaymentStatus status;

    public Payment(Long id, Integer amount, Date paymentDate, Order order, PaymentMethod method, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.order = order;
        this.method = method;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}

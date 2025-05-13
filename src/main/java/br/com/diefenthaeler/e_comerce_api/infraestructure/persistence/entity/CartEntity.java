package br.com.diefenthaeler.e_comerce_api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
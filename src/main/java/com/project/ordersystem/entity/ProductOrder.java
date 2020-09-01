package com.project.ordersystem.entity;
import com.project.ordersystem.enums.ProductOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "ORDERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrder {

    @Id
    @GeneratedValue(generator = "SEQ_ORDERS", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Buyer buyer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderItem> items = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ProductOrderStatus orderStatus = ProductOrderStatus.IN_PROGRESS;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private WareHouseAddress wareHouseAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public ProductOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(ProductOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public WareHouseAddress getWareHouseAddress() {
        return wareHouseAddress;
    }

    public void setWareHouseAddress(WareHouseAddress wareHouseAddress) {
        this.wareHouseAddress = wareHouseAddress;
    }
}


package com.project.ordersystem.entity;
import com.project.ordersystem.enums.OrderItemStatus;
import com.project.ordersystem.enums.OrderItemType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status = OrderItemStatus.PAID;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PREPARATION_TIME")
    private Integer preparationTime;

    @Column(name = "PRICE")
    private BigDecimal price = BigDecimal.ZERO;

    @Column(length = 255, name = "PRODUCT_TITLE")
    private String productTitle;

    public abstract OrderItemType getOrderItemType();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductOrder getOrder() {
        return order;
    }

    public void setOrder(ProductOrder order) {
        this.order = order;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
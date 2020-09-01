package com.project.ordersystem.entity;

import com.project.ordersystem.enums.OrderItemType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Product")
public class ProductOrderItem extends OrderItem {

    @Override
    public OrderItemType getOrderItemType() {
        return OrderItemType.PRODUCT_ORDER_ITEM;
    }
}

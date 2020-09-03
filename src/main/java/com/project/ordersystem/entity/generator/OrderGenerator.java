package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderGenerator extends EntityGenerator<Order> {

    @Override
    public Order generate(EntityGeneratorModel model) {
        Order order = new Order();
        order.setBuyer(model.getBuyer());
        order.setWareHouseAddress(model.getWareHouseAddress());
        order.setOrderDate(model.getOrderDate());
        return order;
    }

    public void addProductPriceToTotalCost(Order order, BigDecimal price) {
        BigDecimal totalPrice = order.getTotalPrice();
        totalPrice = totalPrice.add(price);
        order.setTotalPrice(totalPrice);
    }
}

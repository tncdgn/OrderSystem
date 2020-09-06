package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.project.ordersystem.entity.generator.EntityGeneratorType.*;

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

    @Override
    public EntityGeneratorType type() {
        return ORDER;
    }

    public void addProductPriceToTotalCost(Order order, BigDecimal price) {
        BigDecimal totalPrice = order.getTotalPrice();
        totalPrice = totalPrice.add(price);
        order.setTotalPrice(totalPrice);
    }
}

package com.project.ordersystem.entity.generator;

import com.project.ordersystem.entity.Order;
import com.project.ordersystem.entity.OrderItem;
import com.project.ordersystem.entity.Product;
import com.project.ordersystem.entity.ProductOrderItem;
import com.project.ordersystem.model.ProductModel;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderGenerator extends EntityGenerator<Order> {

    @Override
    public Order generate(EntityGeneratorModel model) {
        Order order = new Order();
        order.setBuyer(model.getBuyer());
        order.setWareHouseAddress(model.getWareHouseAddress());
        return order;
    }

    public void addProductPriceToTotalCost(Order order, BigDecimal price) {
        BigDecimal totalPrice = order.getTotalPrice();
        totalPrice = totalPrice.add(price);
        order.setTotalPrice(totalPrice);
    }
}

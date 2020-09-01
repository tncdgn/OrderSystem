package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderItemDao;
import com.project.ordersystem.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService extends BaseService<OrderItem> {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void save(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    public Optional<OrderItem> findById(long id) {
        return orderItemDao.findById(id);
    }
}

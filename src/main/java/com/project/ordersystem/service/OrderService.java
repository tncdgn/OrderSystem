package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderDao;
import com.project.ordersystem.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderDao.findById(id);
    }
}

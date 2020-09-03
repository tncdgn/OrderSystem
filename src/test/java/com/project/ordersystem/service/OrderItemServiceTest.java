package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderItemDao;
import com.project.ordersystem.entity.*;
import com.project.ordersystem.entity.generator.EntityGeneratorModel;
import com.project.ordersystem.entity.generator.ProductOrderItemGenerator;
import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.model.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class OrderItemServiceTest {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemDao orderItemDao;


    @Test
    public void shouldSave() {
        OrderItem orderitem = new ProductOrderItem();
        orderItemService.save(orderitem);

        verify(orderItemDao).save(orderitem);
    }

    @Test
    public void shouldFindById() {
        orderItemService.findById(1l);

        verify(orderItemDao).findById(1l);
    }
}
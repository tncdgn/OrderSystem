package com.project.ordersystem.service;

import com.project.ordersystem.dao.OrderItemDao;
import com.project.ordersystem.entity.OrderItem;
import com.project.ordersystem.entity.ProductOrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


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
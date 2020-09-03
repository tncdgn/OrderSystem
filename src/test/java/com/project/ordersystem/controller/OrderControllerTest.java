package com.project.ordersystem.controller;

import com.project.ordersystem.exception.InvalidRequestException;
import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.service.MessageResourceService;
import com.project.ordersystem.service.OrderService;
import com.project.ordersystem.validator.OrderValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderValidator orderValidator;

    @Mock
    private OrderService orderService;

    @Mock
    private MessageResourceService messageResourceService;

    @Test
    public void shouldThrowExceptionIfRequestIsInvalid() {
        CreateOrderModel createOrderModel = new CreateOrderModel();

        Mockito.doThrow(new InvalidRequestException("product.id.notValid", new Object())).when(orderValidator).validate(createOrderModel);
        orderController.createOrder(createOrderModel);

        verifyZeroInteractions(orderService);

        verify(messageResourceService).getMessage("product.id.notValid");
    }

    @Test
    public void shouldThrowUnExpectedException() {
        CreateOrderModel createOrderModel = new CreateOrderModel();

        Mockito.doThrow(new NullPointerException()).when(orderValidator).validate(createOrderModel);
        orderController.createOrder(createOrderModel);

        verifyZeroInteractions(orderService);
        verifyZeroInteractions(messageResourceService);
    }

    @Test
    public void shouldCreateOrder() {
        CreateOrderModel createOrderModel = new CreateOrderModel();

        orderController.createOrder(createOrderModel);

        verify(orderValidator).validate(createOrderModel);
        verify(orderService).createOrder(createOrderModel);

        verifyZeroInteractions(messageResourceService);
    }
}
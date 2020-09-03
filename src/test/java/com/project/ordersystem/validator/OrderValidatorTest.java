package com.project.ordersystem.validator;

import com.project.ordersystem.entity.Buyer;
import com.project.ordersystem.entity.Product;
import com.project.ordersystem.exception.InvalidRequestException;
import com.project.ordersystem.model.CreateOrderModel;
import com.project.ordersystem.model.ProductModel;
import org.apache.commons.lang.time.DateUtils;
import org.h2.util.DateTimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderValidatorTest {

    @InjectMocks
    private OrderValidator orderValidator;

    @Mock
    private ProductValidator productValidator;

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfOrderDateInvalid() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getYesterday();

        request.setOrderDate(orderDate);

        orderValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfNoProductInOrder() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getNextYear();
        request.setOrderDate(orderDate);

        orderValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfProductInvalid() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getNextYear();
        request.setOrderDate(orderDate);

        ProductModel productModel = ProductModel.builder().build();
        request.setProductModels(Arrays.asList(productModel));

        Mockito.doThrow(new InvalidRequestException("product.id.notValid", new Object())).when(productValidator).validate(productModel);

        orderValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfBuyerIdNotValid() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getNextYear();
        request.setOrderDate(orderDate);
        request.setBuyerId(-1l);

        ProductModel productModel = ProductModel.builder().build();
        request.setProductModels(Arrays.asList(productModel));

        orderValidator.validate(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfWareHouseIdNotValid() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getNextYear();
        request.setOrderDate(orderDate);
        request.setBuyerId(1l);
        request.setBuyerWareHouseAddressId(-1l);

        ProductModel productModel = ProductModel.builder().build();
        request.setProductModels(Arrays.asList(productModel));

        orderValidator.validate(request);
    }

    @Test
    public void shouldNotThrowException() {
        CreateOrderModel request = new CreateOrderModel();

        Date orderDate = getNextYear();
        request.setOrderDate(orderDate);
        request.setBuyerId(1l);
        request.setBuyerWareHouseAddressId(1l);

        ProductModel productModel = ProductModel.builder().build();
        request.setProductModels(Arrays.asList(productModel));

        try {
            orderValidator.validate(request);
        } catch (Exception e) {
            fail();
        }
    }


    private Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private Date getNextYear() {
        int nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, nextYear);
        return cal.getTime();
    }
}
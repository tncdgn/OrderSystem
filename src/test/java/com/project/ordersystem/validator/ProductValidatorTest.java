package com.project.ordersystem.validator;

import com.project.ordersystem.entity.Product;
import com.project.ordersystem.exception.InvalidRequestException;
import com.project.ordersystem.model.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidatorTest {

    @InjectMocks
    private ProductValidator productValidator;

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfProductIdNotValid() {
        ProductModel productModel = ProductModel.builder().productId(-1l).build();

        productValidator.validate(productModel);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfQuantityNotValid() {
        ProductModel productModel = ProductModel.builder().productId(1l).quantity(-1).build();

        productValidator.validate(productModel);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowExceptionIfStockNotEnough() {
        Product product = Product.builder().id(1l).stock(10).build();
        ProductModel productModel = ProductModel.builder().productId(1l).quantity(15).build();

        productValidator.validateStock(product, Arrays.asList(productModel));
    }

    @Test
    public void shouldNotThrowExceptionIfStockEnough() {
        Product product = Product.builder().id(1l).stock(10).build();
        ProductModel productModel = ProductModel.builder().productId(1l).quantity(8).build();

        try {
            productValidator.validateStock(product, Arrays.asList(productModel));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void shouldNotThrowException() {
        ProductModel productModel = ProductModel.builder().productId(1l).quantity(1).build();

        try {
            productValidator.validate(productModel);
        } catch (Exception e) {
            fail();
        }
    }
}
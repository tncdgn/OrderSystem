package com.project.ordersystem.service;

import com.project.ordersystem.dao.ProductDao;
import com.project.ordersystem.entity.Product;
import com.project.ordersystem.exception.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @Test
    public void shouldSave() {
        Product product = new Product();
        productService.save(product);

        verify(productDao).save(product);
    }

    @Test
    public void shouldFindById() {
        productService.findById(1l);

        verify(productDao).findById(1l);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionIfProductNotFound() {
        when(productDao.findByIdList(Arrays.asList(1l))).thenReturn(Collections.emptyList());

        productService.findOrThrowException(Arrays.asList(1l));
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionIfFoundProductSizeNotEqualGivenSize() {
        Product product1 = Product.builder().id(1l).build();

        when(productDao.findByIdList(Arrays.asList(1l, 2l))).thenReturn(Arrays.asList(product1));

        productService.findOrThrowException(Arrays.asList(1l,2l));
    }

    @Test
    public void shouldFindByIdList() {
        Product product = Product.builder().id(1l).build();

        when(productDao.findByIdList(Arrays.asList(1l))).thenReturn(Arrays.asList(product));

        List<Product> products = productService.findOrThrowException(Arrays.asList(1l));

        assertTrue(products.contains(product));
    }
}
package com.project.ordersystem.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductModelTest {

    @Test
    public void shouldGenerateProductIdAsInt(){
        ProductModel productModel= ProductModel.builder().productId(1l).quantity(2).build();

        assertEquals(productModel.generateProductIdAsInt(),1);
    }

}